(ns examples.generator)

                                        ; START: midinote
(import '[examples.datatypes.midi MidiNote])
(let [min-duration 250
      min-velocity 64
      rand-note (reify
  MidiNote
  (to-msec [this tempo] (+ (rand-int 1000) min-duration))
  (key-number [this] (rand-int 100))
  (play [this tempo midi-channel]
    (let [velocity (+ (rand-int 100) min-velocity)]
      (.noteOn midi-channel (key-number this) velocity)
      (Thread/sleep (to-msec this tempo)))))]
  (perform (repeat 15 rand-note)))
                                        ; END: midinote