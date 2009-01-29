; START: namespace
(ns reader.snake
  (:import (java.awt Color Dimension) 
	   (javax.swing JPanel JFrame Timer JOptionPane)
           (java.awt.event ActionListener KeyListener))
  (:use clojure.contrib.import-static
	[clojure.contrib.seq-utils :only (includes?)]))
(import-static java.awt.event.KeyEvent VK_LEFT VK_RIGHT VK_UP VK_DOWN)
; END: namespace

; TODO: implement the Snake!