!Nethaniel Sanchez
!Ams129
!project
!euler.F90


subroutine eulers_method(t_0, y_0, t_f, N, file_name)
  implicit none
  real, external :: dydt
  real :: t_0, y_0, t_f, N, h, t, y, d
  Character(len=20) :: file_name
  open(unit = 23, file=file_name)
  h = (10/N)
  t = t_0
  y = y_0
  do while (t<=t_f)
    write(23,*) t, y
    d = dydt(t, y)
    y = y + (h*d)
    t = t + h
  end do
  close(23)
end subroutine eulers_method  

real(kind=8) function dydt(t, y)
  implicit none
  real, intent(in) :: t, y
  dydt = (2*t)/(y*(1.+(t*t)))
  return
end function dydt
