(ns examples.test.snake
  (:use clojure.test
        examples.snake))

(deftest test-add-points
  (is (= [2,0] (add-points [1,1] [1,-1]))))

(deftest test-point-to-screen-rect
  (is (= [20 50 10 10] (point-to-screen-rect [2,5]))))

(deftest test-create-apple
  (let [apple (create-apple)]
    (is (<= 0 (first (:location apple)) width))
    (is (<= 0 (second (:location apple)) height))
    (is (= (:apple (:type apple))))))

(let [snake (create-snake)]
  (deftest test-move
    (is (= [[2 1]] (:body (move snake))))
    (is (= [[2 1] [1 1]] (:body (move snake :grow)))))

  (deftest test-turn
    (is (= [-1 0] (:dir (turn snake [-1 0])))))

  (deftest test-win
    (let [growing-snakes (iterate #(move %1 :grow) snake)]
      (is (= (take 4 (map win? growing-snakes)) 
	     (replicate 4 false)))
      (is (= (take 10 (drop 4 (map win? growing-snakes)))
	     (replicate 10 true)))))
	
  (deftest test-lose
    (let [grow #(move %1 :grow)]
      (is (not (lose? snake)))
      (is (not (lose? (grow snake))))
      (is (not (lose? (-> snake grow grow))))
      (is (lose? (-> snake (turn [1 0]) grow (turn [-1 0]) grow)))))

  (deftest test-eats
    (let [eat-me {:location (first (:body snake))}
	  dont-eat {:location [-1 -1]}]
      (is (eats? snake eat-me))
      (is (not (eats? snake dont-eat))))))
