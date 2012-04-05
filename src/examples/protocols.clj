(ns examples.protocols
  (:import (java.net Socket URL)
           (java.io File FileInputStream FileOutputStream
                    InputStream InputStreamReader
                    BufferedReader BufferedWriter
                    OutputStream OutputStreamWriter)))

                                        ; START: gulp-2
(defn gulp [src]
  (let [sb (StringBuilder.)]
    (with-open [reader (make-reader src)]
      (loop [c (.read reader)]
        (if (neg? c)
          (str sb)
          (do
            (.append sb (char c))
            (recur (.read reader))))))))

                                        ; END: gulp-2

                                        ; START: expectorate-2
(defn expectorate [dst content]
  (with-open [writer (make-writer dst)]
    (.write writer (str content))))

                                        ; END: expectorate-2

                                        ; START: make-reader-2
(defn make-reader [src]
  (-> (condp = (type src)
          java.io.InputStream src
          java.lang.String (FileInputStream. src)
          java.io.File (FileInputStream. src)
          java.net.Socket (.getInputStream src)
          java.net.URL (if (= "file" (.getProtocol src))
                         (-> src .getPath FileInputStream.)
                         (.openStream src)))
      InputStreamReader.
      BufferedReader.))

                                        ; END: make-reader-2

                                        ; START: make-writer-2
(defn make-writer [dst]
  (-> (condp = (type dst)
          java.io.OutputStream dst
          java.io.File (FileOutputStream. dst)
          java.lang.String (FileOutputStream. dst)
          java.net.Socket (.getOutputStream dst)
          java.net.URL (if (= "file" (.getProtocol dst))
                         (-> dst .getPath FileOutputStream.)
                         (throw (IllegalArgumentException.
                                 "Can't write to non-file URL"))))
      OutputStreamWriter.
      BufferedWriter.))

                                        ; END: make-writer-2

                                        ; START: extend-input-stream
(extend InputStream
  IOFactory
  {:make-reader (fn [src]
                  (-> src InputStreamReader. BufferedReader.))
   :make-writer (fn [dst]
                  (throw (IllegalArgumentException.
                          "Can't open as an InputStream.")))})

                                        ; END: extend-input-stream

                                        ; START: extend-output-stream
(extend OutputStream
  IOFactory
  {:make-reader (fn [src]
                  (throw
                   (IllegalArgumentException.
                    "Can't open as an OutputStream.")))
   :make-writer (fn [dst]
                  (-> dst OutputStreamWriter. BufferedWriter.))})
                                        ; END: extend-output-stream

                                        ; START: extend-file
(extend-type File
  IOFactory
  (make-reader [src]
    (make-reader (FileInputStream. src)))
  (make-writer [dst]
    (make-writer (FileOutputStream. dst))))
                                        ; END: extend-file

                                        ; START: extend-socket
(extend-protocol IOFactory
  Socket
  (make-reader [src]
    (make-reader (.getInputStream src)))

  (make-writer [dst]
    (make-writer (.getOutputStream dst)))

  URL
  (make-reader [src]
    (make-reader
     (if (= "file" (.getProtocol src))
       (-> src .getPath FileInputStream.)
       (.openStream src))))

  (make-writer [dst]
    (make-writer
     (if (= "file" (.getProtocol dst))
       (-> dst .getPath FileInputStream.)
       (throw (IllegalArgumentException.
               "Can't write to non-file URL"))))))
                                        ; END: extend-socket

                                        ; START: midinote
(defprotocol MidiNote
  (to-msec [this tempo])
  (key-number [this])
  (play [this tempo midi-channel]))
                                        ; END: midinote

                                        ; START: extend-note
(import 'javax.sound.midi.MidiSystem)
(extend-type Note
  MidiNote
  (to-msec [this tempo]
    (let [duration-to-bpm {1 240, 1/2 120, 1/4 60, 1/8 30, 1/16 15}]
      (* 1000 (/ (duration-to-bpm (:duration this))
                 tempo))))
                                        ; END: extend-note

                                        ; START: key-number
  (key-number [this]
    (let [scale {:C 0,  :C# 1, :Db 1,  :D 2,
                 :D# 3, :Eb 3, :E 4,   :F 5,
                 :F# 6, :Gb 6, :G 7,   :G# 8,
                 :Ab 8, :A 9,  :A# 10, :Bb 10,
                 :B 11}]
      (+ (* 12 (inc (:octave this)))
         (scale (:pitch this)))))
                                        ; END: key-number
  
                                        ; START: play
  (play [this tempo midi-channel]
    (let [velocity (or (:velocity this) 64)]
      (.noteOn midi-channel (key-number this) velocity)
      (Thread/sleep (to-msec this tempo)))))
                                        ; END: play
  
                                        ; START: perform
(defn perform [notes & {:keys [tempo] :or {tempo 120}}]
  (with-open [synth (doto (MidiSystem/getSynthesizer) .open)]
    (let [channel (aget (.getChannels synth) 0)]
      (doseq [note notes]
        (play note tempo channel)))))
                                        ; END: perform