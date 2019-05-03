.data
.comm	a,4,4

.text
	.align 4
.globl  main
main:
main_bb2:
main_bb3:
	movl	$2, %EDI
	movl	%EDI, a(%RIP)
	movl	$1, %EDI
	call	test
main_bb4:
	ret
.globl  test
test:
test_bb2:
test_bb3:
	movl	$1, %EAX
test_bb4:
	ret
