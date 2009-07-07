(ns examples.test.codebreaker
  (:use clojure.contrib.test-is
        examples.codebreaker))

(deftest test-codebreaker-score
  (are (= (codebreaker-score _1 _2) _3)
       "rgyc" "rgyc" "bbbb"
       "rgyc" "rgcy" "bbww"
       "rgyc" "yrgc" "bwww"
       "rgyc" "crgy" "wwww"

       "rgyc" "wgyc" "bbb"
       "rgyc" "wryc" "bbw" 
       "rgyc" "wrgc" "bww" 
       "rgyc" "wrgy" "www" 
       
       "rgyc" "wgwc" "bb" 
       "rgyc" "wrwc" "bw" 
       "rgyc" "gwcw" "ww" 
       
       "rgyc" "rwww" "b" 
       "rgyc" "wwrw" "w"))

(deftest test-codes-with-duplicates
  (are (= (codebreaker-score _1 _2) _3)
       "rgrg" "rrgg" "bbww"
       "rggr" "rgrg" "bbww"
       "yyyy" "ybyb" "bb"))