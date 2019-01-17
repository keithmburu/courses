(defun square (x)
  (* x x))

(defun hypotenuse (x y)
  (sqrt (+ (square x))
	(square y)))

(defun abs-val (x)
  (cond (< x 0) (-x)
	 (t x))) ;else return x

(defun abs-val2 (x)
  (if (< x 0)  (-x) x))
