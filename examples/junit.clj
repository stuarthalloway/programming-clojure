(ns examples.junit)

(fn []
; START:create-junit-test
(gen-and-save-class 
 "." "test.WidgetTest"
 :extends junit.framework.TestCase
 :methods [
	   ["testOne" [] (Void/TYPE)]
	   ["testTwo" [] (Void/TYPE)]])
; END:create-junit-test							 
)		

