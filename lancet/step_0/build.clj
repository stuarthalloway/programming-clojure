(use 'lancet)
(use 'lancet.ant)
       
(def src "src")
(def build "classes")

(default-target :compile)

(deftarget init
	(tstamp)
	(mkdir {:dir build}))
	
(deftarget compile
	"Compile Java sources"
	(init)
	(javac {:srcdir src :destdir build}))
