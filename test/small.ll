(DATA  a)
(FUNCTION  main  []
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Load [(r 1)]  [(s a)(i 0)])
    (OPER 5 Mov [(r 2)]  [(i 2)])
    (OPER 6 Mov [(r 1)]  [(r 2)])
    (OPER 7 Store []  [(r 1)(s a)(i 0)])
    (OPER 8 Load [(r 4)]  [(s a)(i 0)])
    (OPER 9 Mov [(r 5)]  [(i 2)])
    (OPER 10 EQ [(r 3)]  [(r 4)(r 5)])
    (OPER 11 BEQ [(r 6)]  [(r 3)(i 0)(bb 5)])
  )
  (BB 4
    (OPER 12 Load [(r 7)]  [(s a)(i 0)])
    (OPER 13 Mov [(r 8)]  [(i 4)])
    (OPER 14 Mov [(r 7)]  [(r 8)])
    (OPER 15 Store []  [(r 7)(s a)(i 0)])
  )
  (BB 5
    (OPER 16 Load [(r 10)]  [(s a)(i 0)])
    (OPER 17 Mov [(r 11)]  [(i 2)])
    (OPER 18 LT [(r 9)]  [(r 10)(r 11)])
    (OPER 19 BEQ [(r 12)]  [(r 9)(i 0)(bb 8)])
  )
  (BB 6
    (OPER 20 Load [(r 13)]  [(s a)(i 0)])
    (OPER 21 Mov [(r 14)]  [(i 5)])
    (OPER 22 Mov [(r 13)]  [(r 14)])
    (OPER 23 Store []  [(r 13)(s a)(i 0)])
  )
  (BB 7
    (OPER 29 Load [(r 18)]  [(s a)(i 0)])
    (OPER 30 Mov [(r 19)]  [(i 2)])
    (OPER 31 GT [(r 17)]  [(r 18)(r 19)])
    (OPER 32 BEQ [(r 20)]  [(r 17)(i 0)(bb 11)])
  )
  (BB 9
    (OPER 33 Load [(r 21)]  [(s a)(i 0)])
    (OPER 34 Mov [(r 22)]  [(i 7)])
    (OPER 35 Mov [(r 21)]  [(r 22)])
    (OPER 36 Store []  [(r 21)(s a)(i 0)])
  )
  (BB 10
    (OPER 46 Mov [(r 29)]  [(i 1)])
    (OPER 47 Pass []  [(r 29)] [(PARAM_NUM 0)])
    (OPER 48 JSR []  [(s test)] [(numParams 1)])
    (OPER 49 Mov [(r 30)]  [(m RetReg)])
    (OPER 50 Mov [(m RetReg)]  [(r 30)])
  )
  (BB 12
    (OPER 41 Load [(r 27)]  [(s a)(i 0)])
    (OPER 42 Mov [(r 28)]  [(i 8)])
    (OPER 43 Mov [(r 27)]  [(r 28)])
    (OPER 44 Store []  [(r 27)(s a)(i 0)])
  )
  (BB 13
    (OPER 45 Jmp []  [(bb 10)])
  )
  (BB 14
    (OPER 51 Func_Exit []  [])
    (OPER 52 Return []  [(m RetReg)])
  )
  (BB 8
    (OPER 24 Load [(r 15)]  [(s a)(i 0)])
    (OPER 25 Mov [(r 16)]  [(i 6)])
    (OPER 26 Mov [(r 15)]  [(r 16)])
    (OPER 27 Store []  [(r 15)(s a)(i 0)])
    (OPER 28 Jmp []  [(bb 7)])
  )
  (BB 11
    (OPER 37 Load [(r 24)]  [(s a)(i 0)])
    (OPER 38 Mov [(r 25)]  [(i 1)])
    (OPER 39 EQ [(r 23)]  [(r 24)(r 25)])
    (OPER 40 BEQ [(r 26)]  [(r 23)(i 0)(bb 13)])
  )
)
(FUNCTION  test  [(int t)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 2)]  [(i 1)])
    (OPER 5 Mov [(m RetReg)]  [(r 2)])
  )
  (BB 4
    (OPER 6 Func_Exit []  [])
    (OPER 7 Return []  [(m RetReg)])
  )
)
