; Inspired by the snakes the have gone before:
; Abhishek Reddy's snake: http://www.plt1.com/1070/even-smaller-snake/
; Mark Volkmann's snake: http://www.ociweb.com/mark/programming/ClojureSnake.html 

; The START:/END: pairs are production artifacts for the book and not 
; part of normal Clojure style

(ns examples.snake
  (:import (java.awt Color) (javax.swing JPanel JFrame Timer JOptionPane)
           (java.awt.event ActionListener KeyListener))
  (:use clojure.contrib.import-static
	[clojure.contrib.seq-utils :only (includes?)]))
(import-static java.awt.event.KeyEvent VK_LEFT VK_RIGHT VK_UP VK_DOWN)

; ----------------------------------------------------------
; functional model
; ----------------------------------------------------------
; START: constants
(def width 75)
(def height 50)
(def point-size 10)
(def turn-millis 75)
(def win-length 5)
(def dirs { VK_LEFT  [-1  0] 
            VK_RIGHT [ 1  0]
            VK_UP    [ 0 -1] 
	    VK_DOWN  [ 0  1]})
; END: constants

; START: board math
(defn add-points [& pts] 
  (vec (apply map + pts)))

(defn point-to-screen-rect [pt] 
  (map #(* point-size %) 
       [(pt 0) (pt 1) 1 1]))
; END: board math

; START: apple
(defn create-apple [] 
  {:location [(rand-int width) (rand-int height)]
   :color (Color. 210 50 90)
   :type :apple}) 
; END: apple

; START: snake
(defn create-snake []
  {:body (list [1 1]) 
   :dir [1 0]
   :type :snake
   :color (Color. 15 160 70)})
; START: snake

; START: move
(defn move [{:keys [body dir] :as snake} & grow]
  (assoc snake :body (cons (add-points (first body) dir) 
			   (if grow body (butlast body)))))
; END: move

; START: turn
(defn turn [snake newdir] 
  (if newdir (assoc snake :dir newdir) snake))
; END: turn

; START: win
(defn win? [{body :body}]
  (>= (count body) win-length))
; END: win

; START: lose
(defn head-overlaps-body? [{[head & body] :body}]
  ; have proposed to SS that argument order be reversed:
  (includes? head body))

(def lose? head-overlaps-body?)
; END: lose

; START: eats
(defn eats? [{[snake-head] :body} {apple :location}]
   (= snake-head apple))
; END: eats

; ----------------------------------------------------------
; mutable model
; ----------------------------------------------------------
(defn update-positions [snake apple]
  (dosync
   (if (eats? @snake @apple)
     (do (ref-set apple (create-apple))
	 (alter snake move :grow))
     (alter snake move))))

(defn update-direction [snake newdir]
  (dosync (alter snake turn newdir)))

(defn reset-game [snake apple]
  (dosync (ref-set apple (create-apple))
	  (ref-set snake (create-snake))))

; ----------------------------------------------------------
; gui
; ----------------------------------------------------------
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

(defn game [] 
  (let [snake (ref (create-snake))
	apple (ref (create-apple))
	frame (JFrame. "Snake")
	panel (proxy [JPanel ActionListener KeyListener] []
		(paintComponent [g] 
		  (proxy-super paintComponent g)
		  (paint g @snake)
		  (paint g @apple))
		(actionPerformed [e]
                  (update-positions snake apple)
		  (when (lose? @snake)
		    (reset-game snake apple)
		    (JOptionPane/showMessageDialog frame "You lose!"))
		  (when (win? @snake)
		    (reset-game snake apple)
		    (JOptionPane/showMessageDialog frame "You win!"))
		  (.repaint this))
		(keyPressed [e] 
		  (update-direction snake (dirs (.getKeyCode e))))
		(keyReleased [e])
		(keyTyped [e]))
	timer (Timer. turn-millis panel)]
    (doto panel 
      (.setFocusable true)
      (.addKeyListener panel))
    (doto frame
      (.add panel)
      (.setSize (* width point-size) (* height point-size))
      (.setVisible true))
    (.start timer)
    [snake, apple, timer]))


