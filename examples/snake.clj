; Inspired by the snakes the have gone before:
; Abhishek Reddy's snake: http://www.plt1.com/1070/even-smaller-snake/
; Mark Volkmann's snake: http://www.ociweb.com/mark/programming/ClojureSnake.html 

(ns examples.snake
  (:import (java.awt Color) (javax.swing JPanel JFrame Timer)
           (java.awt.event ActionListener KeyListener))
  (:use clojure.contrib.import-static))
(import-static java.awt.event.KeyEvent VK_LEFT VK_RIGHT VK_UP VK_DOWN)

; Game board and coordinates. points are [x,y] vectors 
(def width 75)
(def height 50)
(def point-size 10)
(def turn-millis 75)

(defn add-points [& pts] 
  (vec (apply map + pts)))

(defn point-to-screen-rect [pt] 
  (map #(* point-size %) 
       [(pt 0) (pt 1) 1 1]))

(def dirs { VK_LEFT  [-1  0] 
            VK_RIGHT [ 1  0]
            VK_UP    [ 0 -1] 
	    VK_DOWN  [ 0  1]})

; apple
(defn random-apple [] [(rand-int width) (rand-int height)])

(def *apple* (ref (random-apple)))

; snake
(def *snake* (ref {:body (list [1 1]) :dir [1 0]}))

(defn move [{:keys [body dir] :as snake} & grow]
  (assoc snake :body (cons (add-points (first body) dir) 
			   (if grow body (butlast body)))))

(defn turn [snake newdir] 
  (if newdir (assoc snake :dir newdir) snake))

; per-game-turn update
(defn collision? [{[snake-head] :body} apple]
   (= snake-head apple))

(defn update-positions [snake-ref apple-ref]
  (dosync
   (if (collision? @snake-ref @apple-ref)
     (do (ref-set apple-ref (random-apple))
	 (alter snake-ref move :grow))
     (alter snake-ref move))))

; drawing
(def colors {:apple (Color. 210 50 90) :snake (Color. 15 160 70)})

(defn paint [g pt color] 
  (let [[x y width height] (point-to-screen-rect pt)]
    (.setColor g color) 
    (.fillRect g x y width height)))

(defn paint-snake [g {:keys [body]}]
  (doseq [point body]
    (paint g point (colors :snake))))

(defn paint-apple [g apple]
  (paint g apple (colors :apple)))
 
; gui elements
(def panel 
  (proxy [JPanel ActionListener KeyListener] []
    (paintComponent [g] 
      (proxy-super paintComponent g)
      (paint-snake g @*snake*)
      (paint-apple g @*apple*))
    (actionPerformed [e]
      (update-positions *snake* *apple*)
      (.repaint this))
    (keyPressed [e] 
      (dosync (alter *snake* turn (dirs (.getKeyCode e)))))
    (keyReleased [e])
    (keyTyped [e])))
 
(doto panel 
  (.setFocusable true)
  (.addKeyListener panel))

(doto (JFrame. "Snake")
  (.add panel)
  (.setSize (* width point-size) (* height point-size))
  (.setVisible true))
(.start (Timer. turn-millis panel))