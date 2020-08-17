program gauss

  implicit none

  real (kind=8), dimension(3,3) :: A
  real (kind=8), dimension(3) :: b, c

  integer :: i,j,x
  
  real (kind = 8) n

  ! initialize matrix A and vector b
  A(1,:) = (/2., 3., -1./)
  A(2,:) = (/4., 7., 1./)
  A(3,:) = (/7., 10., -4./)

  b = (/1., 3., 4./)

  ! print augmented matrix
  do i = 1, 3           ! i is row
    print *, A(i,:), "|", b(i)
  end do

  print *, ""    ! print a blank line
  print *, "Gaussian elimination........"
  ! gaussian elimination
  do j = 1, 2           ! j is column
    do i = j+1, 3       ! i is row
      ! STUDENTS: PLEASE FINISH THIS GAUSSIAN ELIMINATION
      do x = 1, 3       ! x is column
        if(j==1) then
          if(i==2) then
      	    n = (A(1,x)*(-2)) + A(2,x)       ! n = (row1*-2)+row2
      	    A(2,x) = n                     ! row2 replaced by n
      	  end if
      	  if(i==3) then
      	    n = (A(1,x)*(-7)) + (A(3,x)*2)   ! n = (row1*-7)+(row3*2)
      	    A(3,x) = n 					   ! row3 replaced by n
      	  end if
      	end if
      	if(j==2) then
      	  if(i==3) then
      	    n = A(2,x) + A(3,x) 		   ! n = row2 + row3
      	    A(3,x) = n                     ! row3 replaced by n
      	  end if
      	end if
      end do
      if(j==1) then
        if(i==2) then
      	  n = (b(1)*(-2)) + b(2)             ! n = (b1*2) + b2
      	  b(2) = n                         ! b2 = n
      	end if
      	if(i==3) then
      	  n = (b(1)*(-7)) + (b(3)*2)         ! n = (b1*-7) + (b(3)*2)
      	  b(3) = n
        end if
      end if
      if(j==2) then
        if(i==3) then
	      n = b(2) + b(3)                   ! n = b2 + b3
	      b(3) = n                          ! b3 = n
        end if
      end if
    end do
  end do
  ! print augmented matrix again
  ! this should be an echelon form (or triangular form)
  print *, "***********************"
  do i = 1, 3
    print *, A(i,:), "|", b(i)
  end do

  print *, ""    ! print a blank line
  print *, "back subs......"

  ! doing back substitution
  do j = 3,2,-1                ! j is column
    do i = j-1,1,-1            ! i is row
      ! STUDENTS: PLEASE FINISH THIS BACK SUBSTITUTION
      ! HINT: THIS PART IS PRETTY MUCH SIMILAR WITH GAUSSIAN ELIM. PART.
      do x = 1, 3       ! x is column
        if(j==3) then
          if(i==2) then
      	    n = (A(2,x)*2) + (A(3,x)*(-3))       ! n = (row2*2)+(row3*-3)
      	    A(2,x) = n                           ! row2 replaced by n
      	  end if
      	  if(i==1) then
      	    n = (A(3,x)/2) + A(1,x)          ! n = (row3*1/2)+row1
      	    A(1,x) = n 					         ! row1 replaced by n
      	  end if
      	end if
      	if(j==2) then
      	  if(i==1) then
      	    n = (A(2,x)*(-3)) + (A(1,x)*2) 	     ! n = (row2*-3) + (row1*2)
      	    A(1,x) = n                           ! row1 replaced by n
      	  end if
      	end if
      end do
      if(j==3) then
        if(i==2) then
      	  n = (b(2)*2) + (b(3)*(-3))             ! n = (b2*2) + (b3*-3)
      	  b(2) = n                               ! b2 = n
      	end if
      	if(i==1) then
      	  n = (b(3)/2) + b(1)                ! n = (b3*1/2) + b(1)
      	  b(1) = n
        end if
      end if
      if(j==2) then
        if(i==1) then
	      n = (b(2)*(-3)) + (b(1)*2)             ! n = b2*-3 + b1*2
	      b(1) = n                             ! b1 = n
        end if
      end if
    end do
  end do

  ! print the results
  print *, "***********************"
  do i = 1, 3
    print *, A(i,:), "|", b(i)
  end do

  print *, "The solutions are:"
  ! STUDENTS: PLEASE CALCULATE A SOLUTION VECTOR, AND PRINT TO THE SCREEN.
  c(1) = b(1)/A(1,1)
  c(2) = b(2)/A(2,2)
  c(3) = b(3)/A(3,3)
  do i = 1, 3           ! i is row
    print *, c(i)
  end do
end program gauss