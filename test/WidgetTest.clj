(ns test
    (import (junit.framework Assert)))

(defn WidgetTest-testOne [_]
  (Assert/assertEquals 1 1))

(defn WidgetTest-testTwo [_]
  (Assert/fail "fix me"))
