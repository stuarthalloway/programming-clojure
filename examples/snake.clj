; Inspired by the snakes the have gone before:
; Abhishek Reddy's snake: http://www.plt1.com/1070/even-smaller-snake/
; Mark Volkmann's snake: http://www.ociweb.com/mark/programming/ClojureSnake.html 

(ns examples.snake
  (:import (java.awt Color) (javax.swing JPanel JFrame Timer JOptionPane)
           (java.awt.event ActionListener KeyListener))
  (:use clojure.contrib.import-static
	[clojure.contrib.seq-utils :only (includes?)]))
(import-static java.awt.event.KeyEvent VK_LEFT VK_RIGHT VK_UP VK_DOWN)

; Game board and coordinates. points are [x,y] vectors 
(def width 75)
(def height 50)
(def point-size 10)
(def turn-millis 75)
(def win-length 5)

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
(def *apple* (ref nil))

(defn create-apple [] 
  {:location [(rand-int width) (rand-int height)]
   :color (Color. 210 50 90)
   :type :apple}) 

; snake
(def *snake* (ref nil))

(defn create-snake []
  {:body (list [1 1]) 
   :dir [1 0]
   :type :snake
   :color (Color. 15 160 70)})


(defn move [{:keys [body dir] :as snake} & grow]
  (assoc snake :body (cons (add-points (first body) dir) 
			   (if grow body (butlast body)))))

(defn turn [snake newdir] 
  (if newdir (assoc snake :dir newdir) snake))

(defn win? [{body :body}]
  (>= (count body) win-length))

(defn head-overlaps-body? [{[head & body] :body}]
  ; have proposed to SS that argument order be reversed:
  (includes? head body))

(def lose? head-overlaps-body?)

(defn collision? [{[snake-head] :body} {apple :location}]
   (= snake-head apple))

; state updates
(defn update-positions [snake apple]
  (dosync
   (if (collision? @snake @apple)
     (do (ref-set apple (create-apple))
	 (alter snake move :grow))
     (alter snake move))))

(defn update-direction [snake newdir]
  (dosync (alter snake turn newdir)))

(defn reset-game []
  (dosync (ref-set *apple* (create-apple))
	  (ref-set *snake* (create-snake))))

(reset-game)

; drawing
(defn fill-point [g pt color] 
  (let [[x y width height] (point-to-screen-rect pt)]
    (.setColor g color) 
    (.fillRect g x y width height)))

(defmulti paint (fn [g object & _] (:type object)))

(defmethod paint :snake [g {:keys [body color]}]
  (doseq [point body]
    (fill-point g point color)))

(defmethod paint :apple [g {:keys [location color]}]
  (fill-point g location color))
 
; gui elements
(def frame (JFrame. "Snake"))

(def panel 
  (proxy [JPanel ActionListener KeyListener] []
    (paintComponent [g] 
      (proxy-super paintComponent g)
      (paint g @*snake*)
      (paint g @*apple*))
    (actionPerformed [e]
      (update-positions *snake* *apple*)
      (when (lose? @*snake*)
	(reset-game)
	(JOptionPane/showMessageDialog frame "You lose!"))
      (when (win? @*snake*)
	(reset-game)
	(JOptionPane/showMessageDialog frame "You win!"))
      (.repaint this))
    (keyPressed [e] 
      (update-direction *snake* (dirs (.getKeyCode e))))
    (keyReleased [e])
    (keyTyped [e])))

(def timer (Timer. turn-millis panel))
 
(doto panel 
  (.setFocusable true)
  (.addKeyListener panel))

(doto frame
  (.add panel)
  (.setSize (* width point-size) (* height point-size))
  (.setVisible true))
(.start timer)


