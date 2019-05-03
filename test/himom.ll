(DATA  a)
(FUNCTION  addThem  [(int d) (int e)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Add_I [(r 4)]  [(r 1)(r 2)])
    (OPER 5 Mov [(r 3)]  [(r 4)])
    (OPER 6 Mov [(m RetReg)]  [(r 3)])
  )
  (BB 4
    (OPER 7 Func_Exit []  [])
    (OPER 8 Return []  [(m RetReg)])
  )
)
(FUNCTION  putDigit  [(int s)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 3)]  [(i 48)])
    (OPER 5 Add_I [(r 2)]  [(r 3)(r 1)])
    (OPER 6 Pass []  [(r 2)] [(PARAM_NUM 0)])
    (OPER 7 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 8 Mov [(r 4)]  [(m RetReg)])
  )
  (BB 4
    (OPER 9 Func_Exit []  [])
    (OPER 10 Return []  [(m RetReg)])
  )
)
(FUNCTION  printInt  [(int r)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 4)]  [(i 0)])
    (OPER 5 Mov [(r 3)]  [(r 4)])
    (OPER 6 Mov [(r 6)]  [(i 10000)])
    (OPER 7 GTE [(r 5)]  [(r 1)(r 6)])
    (OPER 8 BEQ [(r 7)]  [(r 5)(i 0)(bb 6)])
  )
  (BB 4
    (OPER 9 Mov [(r 8)]  [(i 45)])
    (OPER 10 Pass []  [(r 8)] [(PARAM_NUM 0)])
    (OPER 11 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 12 Mov [(r 9)]  [(m RetReg)])
    (OPER 13 Mov [(r 10)]  [(i 1)])
    (OPER 14 Pass []  [(r 10)] [(PARAM_NUM 0)])
    (OPER 15 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 16 Mov [(r 11)]  [(m RetReg)])
  )
  (BB 5
  )
  (BB 7
    (OPER 20 Mov [(r 16)]  [(i 1000)])
    (OPER 21 Div_I [(r 15)]  [(r 1)(r 16)])
    (OPER 22 Mov [(r 2)]  [(r 15)])
    (OPER 23 Pass []  [(r 2)] [(PARAM_NUM 0)])
    (OPER 24 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 25 Mov [(r 17)]  [(m RetReg)])
    (OPER 26 Mov [(r 20)]  [(i 1000)])
    (OPER 27 Mul_I [(r 19)]  [(r 2)(r 20)])
    (OPER 28 Sub_I [(r 18)]  [(r 19)(r 1)])
    (OPER 29 Mov [(r 1)]  [(r 18)])
    (OPER 30 Mov [(r 21)]  [(i 1)])
    (OPER 31 Mov [(r 3)]  [(r 21)])
  )
  (BB 8
    (OPER 32 Mov [(r 23)]  [(i 100)])
    (OPER 33 GTE [(r 22)]  [(r 1)(r 23)])
    (OPER 34 BEQ [(r 24)]  [(r 22)(i 0)(bb 11)])
  )
  (BB 9
    (OPER 35 Mov [(r 26)]  [(i 100)])
    (OPER 36 Div_I [(r 25)]  [(r 1)(r 26)])
    (OPER 37 Mov [(r 2)]  [(r 25)])
    (OPER 38 Pass []  [(r 2)] [(PARAM_NUM 0)])
    (OPER 39 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 40 Mov [(r 27)]  [(m RetReg)])
    (OPER 41 Mov [(r 30)]  [(i 100)])
    (OPER 42 Mul_I [(r 29)]  [(r 2)(r 30)])
    (OPER 43 Sub_I [(r 28)]  [(r 29)(r 1)])
    (OPER 44 Mov [(r 1)]  [(r 28)])
    (OPER 45 Mov [(r 31)]  [(i 1)])
    (OPER 46 Mov [(r 3)]  [(r 31)])
  )
  (BB 10
    (OPER 55 Mov [(r 38)]  [(i 10)])
    (OPER 56 GTE [(r 37)]  [(r 1)(r 38)])
    (OPER 57 BEQ [(r 39)]  [(r 37)(i 0)(bb 16)])
  )
  (BB 12
    (OPER 50 Mov [(r 35)]  [(i 0)])
    (OPER 51 Pass []  [(r 35)] [(PARAM_NUM 0)])
    (OPER 52 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 53 Mov [(r 36)]  [(m RetReg)])
  )
  (BB 13
    (OPER 54 Jmp []  [(bb 10)])
  )
  (BB 14
    (OPER 58 Mov [(r 41)]  [(i 10)])
    (OPER 59 Div_I [(r 40)]  [(r 1)(r 41)])
    (OPER 60 Mov [(r 2)]  [(r 40)])
    (OPER 61 Pass []  [(r 2)] [(PARAM_NUM 0)])
    (OPER 62 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 63 Mov [(r 42)]  [(m RetReg)])
    (OPER 64 Mov [(r 45)]  [(i 10)])
    (OPER 65 Mul_I [(r 44)]  [(r 2)(r 45)])
    (OPER 66 Sub_I [(r 43)]  [(r 44)(r 1)])
    (OPER 67 Mov [(r 1)]  [(r 43)])
  )
  (BB 15
    (OPER 76 Pass []  [(r 1)] [(PARAM_NUM 0)])
    (OPER 77 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 78 Mov [(r 51)]  [(m RetReg)])
    (OPER 79 Jmp []  [(bb 5)])
  )
  (BB 17
    (OPER 71 Mov [(r 49)]  [(i 0)])
    (OPER 72 Pass []  [(r 49)] [(PARAM_NUM 0)])
    (OPER 73 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 74 Mov [(r 50)]  [(m RetReg)])
  )
  (BB 18
    (OPER 75 Jmp []  [(bb 15)])
  )
  (BB 19
    (OPER 80 Func_Exit []  [])
    (OPER 81 Return []  [(m RetReg)])
  )
  (BB 11
    (OPER 47 Mov [(r 33)]  [(i 1)])
    (OPER 48 EQ [(r 32)]  [(r 3)(r 33)])
    (OPER 49 BEQ [(r 34)]  [(r 32)(i 0)(bb 13)])
  )
  (BB 16
    (OPER 68 Mov [(r 47)]  [(i 1)])
    (OPER 69 EQ [(r 46)]  [(r 3)(r 47)])
    (OPER 70 BEQ [(r 48)]  [(r 46)(i 0)(bb 18)])
  )
  (BB 6
    (OPER 17 Mov [(r 13)]  [(i 1000)])
    (OPER 18 GTE [(r 12)]  [(r 1)(r 13)])
    (OPER 19 BEQ [(r 14)]  [(r 12)(i 0)(bb 8)])
  )
)
(FUNCTION  main  []
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 6)]  [(i 5)])
    (OPER 5 Mov [(r 2)]  [(r 6)])
    (OPER 6 Mov [(r 1)]  [(r 2)])
    (OPER 7 Mov [(r 8)]  [(i 5)])
    (OPER 8 EQ [(r 7)]  [(r 1)(r 8)])
    (OPER 9 BEQ [(r 9)]  [(r 7)(i 0)(bb 6)])
  )
  (BB 4
    (OPER 10 Load [(r 10)]  [(s a)(i 0)])
    (OPER 11 Mov [(r 11)]  [(i 3)])
    (OPER 12 Mov [(r 10)]  [(r 11)])
    (OPER 13 Store []  [(r 10)(s a)(i 0)])
  )
  (BB 5
    (OPER 19 Mov [(r 14)]  [(i 0)])
    (OPER 20 Mov [(r 3)]  [(r 14)])
    (OPER 21 Mov [(r 15)]  [(i 1)])
    (OPER 22 Mov [(r 5)]  [(r 15)])
    (OPER 23 Mov [(r 17)]  [(i 8)])
    (OPER 24 LTE [(r 16)]  [(r 5)(r 17)])
    (OPER 25 BEQ [(r 18)]  [(r 16)(i 0)(bb 8)])
  )
  (BB 7
    (OPER 26 Add_I [(r 19)]  [(r 3)(r 5)])
    (OPER 27 Mov [(r 3)]  [(r 19)])
    (OPER 28 Mov [(r 21)]  [(i 1)])
    (OPER 29 Add_I [(r 20)]  [(r 5)(r 21)])
    (OPER 30 Mov [(r 5)]  [(r 20)])
    (OPER 31 Mov [(r 23)]  [(i 8)])
    (OPER 32 LTE [(r 22)]  [(r 5)(r 23)])
    (OPER 33 BNE [(r 18)]  [(r 22)(i 0)(bb 7)])
  )
  (BB 8
    (OPER 34 Mov [(r 25)]  [(i 3)])
    (OPER 35 Div_I [(r 24)]  [(r 3)(r 25)])
    (OPER 36 Mov [(r 4)]  [(r 24)])
    (OPER 37 Mov [(r 27)]  [(i 4)])
    (OPER 38 Mul_I [(r 26)]  [(r 4)(r 27)])
    (OPER 39 Mov [(r 3)]  [(r 26)])
    (OPER 40 Load [(r 28)]  [(s a)(i 0)])
    (OPER 41 Pass []  [(r 28)] [(PARAM_NUM 0)])
    (OPER 42 Pass []  [(r 1)] [(PARAM_NUM 1)])
    (OPER 43 JSR []  [(s addThem)] [(numParams 2)])
    (OPER 44 Mov [(r 29)]  [(m RetReg)])
    (OPER 45 Mov [(r 2)]  [(r 29)])
    (OPER 46 Mov [(r 30)]  [(i 56)])
    (OPER 47 Pass []  [(r 30)] [(PARAM_NUM 0)])
    (OPER 48 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 49 Mov [(r 31)]  [(m RetReg)])
    (OPER 50 Mov [(r 32)]  [(i 61)])
    (OPER 51 Pass []  [(r 32)] [(PARAM_NUM 0)])
    (OPER 52 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 53 Mov [(r 33)]  [(m RetReg)])
    (OPER 54 Add_I [(r 34)]  [(r 2)(r 3)])
    (OPER 55 Pass []  [(r 34)] [(PARAM_NUM 0)])
    (OPER 56 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 57 Mov [(r 35)]  [(m RetReg)])
    (OPER 58 Mov [(r 36)]  [(i 10)])
    (OPER 59 Pass []  [(r 36)] [(PARAM_NUM 0)])
    (OPER 60 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 61 Mov [(r 37)]  [(m RetReg)])
    (OPER 62 Mov [(r 38)]  [(i 0)])
    (OPER 63 Mov [(r 5)]  [(r 38)])
    (OPER 64 Mov [(r 40)]  [(i 10)])
    (OPER 65 LT [(r 39)]  [(r 5)(r 40)])
    (OPER 66 BEQ [(r 41)]  [(r 39)(i 0)(bb 10)])
  )
  (BB 9
    (OPER 67 Mov [(r 43)]  [(i 48)])
    (OPER 68 Add_I [(r 42)]  [(r 43)(r 5)])
    (OPER 69 Pass []  [(r 42)] [(PARAM_NUM 0)])
    (OPER 70 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 71 Mov [(r 44)]  [(m RetReg)])
    (OPER 72 Mov [(r 46)]  [(i 1)])
    (OPER 73 Add_I [(r 45)]  [(r 5)(r 46)])
    (OPER 74 Mov [(r 5)]  [(r 45)])
    (OPER 75 Mov [(r 48)]  [(i 10)])
    (OPER 76 LT [(r 47)]  [(r 5)(r 48)])
    (OPER 77 BNE [(r 41)]  [(r 47)(i 0)(bb 9)])
  )
  (BB 10
    (OPER 78 Mov [(r 49)]  [(i 10)])
    (OPER 79 Pass []  [(r 49)] [(PARAM_NUM 0)])
    (OPER 80 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 81 Mov [(r 50)]  [(m RetReg)])
    (OPER 82 Mov [(r 51)]  [(i 67)])
    (OPER 83 Pass []  [(r 51)] [(PARAM_NUM 0)])
    (OPER 84 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 85 Mov [(r 52)]  [(m RetReg)])
    (OPER 86 Mov [(r 53)]  [(i 83)])
    (OPER 87 Pass []  [(r 53)] [(PARAM_NUM 0)])
    (OPER 88 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 89 Mov [(r 54)]  [(m RetReg)])
    (OPER 90 Mov [(r 55)]  [(i 3510)])
    (OPER 91 Pass []  [(r 55)] [(PARAM_NUM 0)])
    (OPER 92 JSR []  [(s printInt)] [(numParams 1)])
    (OPER 93 Mov [(r 56)]  [(m RetReg)])
    (OPER 94 Mov [(r 57)]  [(i 10)])
    (OPER 95 Pass []  [(r 57)] [(PARAM_NUM 0)])
    (OPER 96 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 97 Mov [(r 58)]  [(m RetReg)])
    (OPER 98 Mov [(r 59)]  [(i 0)])
    (OPER 99 Mov [(r 1)]  [(r 59)])
    (OPER 100 Mov [(r 60)]  [(i 1)])
    (OPER 101 Mov [(r 2)]  [(r 60)])
    (OPER 102 Mov [(r 61)]  [(i 1)])
    (OPER 103 Mov [(r 3)]  [(r 61)])
    (OPER 104 Mov [(r 62)]  [(i 0)])
    (OPER 105 Mov [(r 4)]  [(r 62)])
    (OPER 106 Mov [(r 63)]  [(i 0)])
    (OPER 107 Mov [(r 5)]  [(r 63)])
    (OPER 108 Mov [(r 65)]  [(i 0)])
    (OPER 109 EQ [(r 64)]  [(r 1)(r 65)])
    (OPER 110 BEQ [(r 66)]  [(r 64)(i 0)(bb 13)])
  )
  (BB 11
    (OPER 111 Mov [(r 68)]  [(i 0)])
    (OPER 112 EQ [(r 67)]  [(r 2)(r 68)])
    (OPER 113 BEQ [(r 69)]  [(r 67)(i 0)(bb 16)])
  )
  (BB 14
    (OPER 114 Mov [(r 70)]  [(i 1)])
    (OPER 115 Mov [(r 5)]  [(r 70)])
  )
  (BB 15
  )
  (BB 17
    (OPER 119 Mov [(r 74)]  [(i 2)])
    (OPER 120 Mov [(r 5)]  [(r 74)])
  )
  (BB 18
    (OPER 130 Jmp []  [(bb 15)])
  )
  (BB 20
    (OPER 124 Mov [(r 78)]  [(i 10)])
    (OPER 125 Mov [(r 5)]  [(r 78)])
  )
  (BB 21
    (OPER 129 Jmp []  [(bb 18)])
  )
  (BB 12
    (OPER 134 Mov [(r 82)]  [(i 10)])
    (OPER 135 EQ [(r 81)]  [(r 5)(r 82)])
    (OPER 136 BEQ [(r 83)]  [(r 81)(i 0)(bb 25)])
  )
  (BB 23
    (OPER 137 Mov [(r 84)]  [(i 99)])
    (OPER 138 Pass []  [(r 84)] [(PARAM_NUM 0)])
    (OPER 139 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 140 Mov [(r 85)]  [(m RetReg)])
    (OPER 141 Mov [(r 86)]  [(i 0)])
    (OPER 142 Pass []  [(r 86)] [(PARAM_NUM 0)])
    (OPER 143 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 144 Mov [(r 87)]  [(m RetReg)])
    (OPER 145 Mov [(r 88)]  [(i 0)])
    (OPER 146 Pass []  [(r 88)] [(PARAM_NUM 0)])
    (OPER 147 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 148 Mov [(r 89)]  [(m RetReg)])
    (OPER 149 Mov [(r 90)]  [(i 108)])
    (OPER 150 Pass []  [(r 90)] [(PARAM_NUM 0)])
    (OPER 151 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 152 Mov [(r 91)]  [(m RetReg)])
  )
  (BB 24
    (OPER 173 Mov [(r 101)]  [(i 10)])
    (OPER 174 Pass []  [(r 101)] [(PARAM_NUM 0)])
    (OPER 175 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 176 Mov [(r 102)]  [(m RetReg)])
    (OPER 177 Mov [(r 103)]  [(i 0)])
    (OPER 178 Mov [(m RetReg)]  [(r 103)])
  )
  (BB 26
    (OPER 179 Func_Exit []  [])
    (OPER 180 Return []  [(m RetReg)])
  )
  (BB 6
    (OPER 14 Load [(r 12)]  [(s a)(i 0)])
    (OPER 15 Mov [(r 13)]  [(i 4)])
    (OPER 16 Mov [(r 12)]  [(r 13)])
    (OPER 17 Store []  [(r 12)(s a)(i 0)])
    (OPER 18 Jmp []  [(bb 5)])
  )
  (BB 22
    (OPER 126 Mov [(r 79)]  [(i 3)])
    (OPER 127 Mov [(r 5)]  [(r 79)])
    (OPER 128 Jmp []  [(bb 21)])
  )
  (BB 19
    (OPER 121 Mov [(r 76)]  [(i 0)])
    (OPER 122 EQ [(r 75)]  [(r 4)(r 76)])
    (OPER 123 BEQ [(r 77)]  [(r 75)(i 0)(bb 22)])
  )
  (BB 16
    (OPER 116 Mov [(r 72)]  [(i 0)])
    (OPER 117 EQ [(r 71)]  [(r 3)(r 72)])
    (OPER 118 BEQ [(r 73)]  [(r 71)(i 0)(bb 19)])
  )
  (BB 13
    (OPER 131 Mov [(r 80)]  [(i 0)])
    (OPER 132 Mov [(r 5)]  [(r 80)])
    (OPER 133 Jmp []  [(bb 12)])
  )
  (BB 25
    (OPER 153 Mov [(r 92)]  [(i 98)])
    (OPER 154 Pass []  [(r 92)] [(PARAM_NUM 0)])
    (OPER 155 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 156 Mov [(r 93)]  [(m RetReg)])
    (OPER 157 Mov [(r 94)]  [(i 97)])
    (OPER 158 Pass []  [(r 94)] [(PARAM_NUM 0)])
    (OPER 159 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 160 Mov [(r 95)]  [(m RetReg)])
    (OPER 161 Mov [(r 96)]  [(i 100)])
    (OPER 162 Pass []  [(r 96)] [(PARAM_NUM 0)])
    (OPER 163 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 164 Mov [(r 97)]  [(m RetReg)])
    (OPER 165 Mov [(r 98)]  [(i 61)])
    (OPER 166 Pass []  [(r 98)] [(PARAM_NUM 0)])
    (OPER 167 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 168 Mov [(r 99)]  [(m RetReg)])
    (OPER 169 Pass []  [(r 5)] [(PARAM_NUM 0)])
    (OPER 170 JSR []  [(s printInt)] [(numParams 1)])
    (OPER 171 Mov [(r 100)]  [(m RetReg)])
    (OPER 172 Jmp []  [(bb 24)])
  )
)