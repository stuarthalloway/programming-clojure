(use 'lancet)
(use 'lancet.ant)
       
(def src "src")
(def build "classes")

(deftarget init
	(tstamp)
	(mkdir {:dir build}))
	
(deftarget compile
	"Compile Java sources"
	(init)
	(javac {:srcdir src :destdir build}))
