CodeHighlighter.addStyle("clojure",{
	"comment": {
		exp: /;[^\n]+/
	},
	"parens": {
		exp: /\(|\)/
	},
	"brackets": {
		exp: /\[|\]/
	},
	"curlybrackets": {
		exp: /\{|\}/
	},
	"keyword": {
	  exp: /(:[\w|-]+)/
	},
	"specialops": {
		exp: /\b(new|quote|\&|var|set\!|monitor-enter|recur|\.|do|fn\*|throw|monitor-exit|finally|let\*|loop\*|try|catch|if|def)\b/
	},
	"function": {
		exp: /\b(def|sorted-map|read-line|re-pattern|keyword\?|val|!|max-key|list\*|ns-aliases|\=\=|longs|special-form-anchor|instance\?|syntax-symbol-anchor|format|sequential\?|fn\?|empty|dorun|gensym|not\=|unchecked-multiply|bit-or|aset-byte|hash-set|add-watch|unchecked-dec|some|nil\?|string\?|second|keys|long-array|bit-set|sorted\?|ns-unalias|ns-publics|all-ns|long|add-classpath|false\?|await1|true\?|short|ns-unmap|repeat|zipmap|distinct|get-in|bit-xor|complement|get-validator|derive|aset-float|scan|commute|rem|set-validator|odd\?|symbol\?|proxy-call-with-super|ns-interns|re-matches|split-with|find-doc|rfirst|gen-and-load-class|import|symbol|vals|print-doc|select-keys|re-matcher|rand|deref|unchecked-inc|read|make-hierarchy|\+|number\?|descendants|into-array|last|unchecked-negate|integer\?|alter|prn|with-meta|floats|\*|fnseq|butlast|-|reversible\?|rseq|send-off|seq\?|identical\?|print|bit-flip|zero\?|bit-and|re-groups|newline|cache-seq|replicate|distinct\?|remove-ns|ratio\?|xml-seq|vec|concat|update-in|vector|conj|bases|\/|unchecked-add|ref-set|assoc|seque|aset-char|boolean|read-string|neg\?|float-array|doubles|isa\?|remove-watch|print-str|gen-and-save-class|rsubseq|vector\?|split-at|ns-refers|create-struct|int-array|float|rrest|map|double-array|accessor|class\?|rand-int|aset-short|prn-str|iterate|slurp|mapcat|assoc-in|special-symbol\?|ref|find-var|inc|unchecked-subtract|ns-name|re-find|bit-not|construct-proxy|destructure|seq|to-array-2d|sorted-map-by|filter|var\?|key|class|re-seq|empty\?|test|create-ns|name|list\?|nthrest|aset|doall|macroexpand-1|not-any\?|resultset-seq|into|ffirst|bit-clear|load-reader|hash|print-ctor|associative\?|float\?|drop-last|replace|decimal\?|parents|map\?|quot|file-seq|send|reverse|count|get-proxy-class|set|comp|nth|byte|constantly|load|namespace|pr-str|<|rationalize|sort-by|cycle|peek|reduce|interleave|cons|macroexpand|var-set|str|aset-boolean|ns-imports|first|bean|\=|var-get|range|tree-seq|aset-double|enumeration-seq|ensure|find-ns|not-every\?|struct-map|>|max|proxy-mappings|identity|ints|min-key|subs|agent-errors|clear-agent-errors|printf|ns-resolve|method-sig|>\=|shutdown-agents|even\?|require|bit-shift-left|touch|compare|cast|supers|load-string|get|<\=|await|resolve|loaded-libs|force|partial|pmap|comparator|pos\?|char|take-while|refer|underive|in-ns|iterator-seq|ancestors|partition|contains\?|update-proxy|interpose|aset-int|load-file|apply|subvec|rest|keyword|ns-map|int|bigdec|aset-long|struct|array-map|bigint|dec|println|aget|pr|drop|gen-class|eval|aclone|pop|bit-shift-right|delay\?|num|disj|rational\?|merge-with|take-nth|double|line-seq|take|set\?|make-array|alias|use|alength|to-array|hash-map|bit-and-not|repeatedly|frest|remove|find|coll\?|drop-while|not-empty|print-special-doc|println-str|list|every\?|flush|sort|dissoc|not|agent|sorted-set|alter-var-root|merge|subseq|min|print-simple|bit-test|await-for|meta|unchecked-divide|rename-keys|union|select|project|join|intersection|map-invert|difference|rename|index|startparse-sax|parse|emit-element|emit|lefts|down|insert-left|up|next|path|children|vector-zip|append-child|zipper|branch\?|end\?|edit|replace|insert-right|root|insert-child|seq-zip|xml-zip|make-node|rights|node|right|left|remove|pany|par|pdistinct|pfilter-dupes|pfilter-nils|pmax|pmin|preduce|psort|psummary|pvec)\b/
	},
	"macro": {
		exp: /\b(time|remove-method|doseq|for|cond|fn|dosync|with-open|sync|let|dotimes|defmethod|lazy-cat|defstruct|with-in-str|loop|with-out-str|when-not|refer-clojure|\.\.|doto|proxy-super|assert|memfn|when-first|definline|defn-|comment|ns|with-precision|or|defn|with-local-vars|when-let|amap|->|defmacro|prefer-method|if-let|and|lazy-cons|declare|locking|delay|defmulti|proxy|defonce|when|areduce|binding|doc)\b/
	},
	"string": {
		exp: /"[^"]*"/
	},
	/* "preencoded" is just a hack to stop the comment rule from inseting a semicolon after -> */
	"preencoded": {
		exp: /&gt;|&lt;|&amp;/
	}
});