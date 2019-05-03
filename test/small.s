.text
	.align 4
.globl  main
main:
main_bb2:
main_bb3:
	movl	$2, %EAX
	movl	%EAX, %EDI
	movl	$2, %EAX
	cmpl	%EAX, %EDI
	jle	main_bb6
main_bb4:
	movl	$7, %EAX
	movl	%EAX, %EDI
main_bb5:
	movl	$2, %EAX
	cmpl	%EAX, %EDI
	jne	main_bb11
main_bb9:
	movl	$9, %EAX
main_bb10:
	movl	$7, %EAX
main_bb14:
	ret
main_bb7:
	movl	$8, %EAX
	movl	%EAX, %EDI
main_bb8:
	jmp	main_bb5
main_bb6:
	movl	$1, %EAX
	cmpl	%EAX, %EDI
	jne	main_bb5
	jmp	main_bb7
main_bb12:
	movl	$10, %EAX
main_bb13:
	jmp	main_bb10
main_bb11:
	movl	$1, %EAX
	cmpl	%EAX, %EDI
	jle	main_bb10
	jmp	main_bb12
