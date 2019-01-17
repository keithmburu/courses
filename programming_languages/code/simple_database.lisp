(setq names (list '(Ada Lovelace) '(Alan Turing) '(Alonzo Church)))
(defun get-name(name-list index)
  (nth index name-list))

(get-name(names 0))
