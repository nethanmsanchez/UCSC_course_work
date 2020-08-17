program main
	implicit none
	
	real (kind=8) :: threshold
	print *, "enter the desired threshold as a real number:"
	read *, threshold
	
	call calc_basel(threshold)

end program main