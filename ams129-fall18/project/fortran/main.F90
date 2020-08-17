!Nethaniel Sanchez
!Ams129
!project
!main.F90

program main
  implicit none
  real :: t_0, y_0, t_f, N
  Character(len=20) :: output
  output = "output_8.txt"
  t_0 = 0
  y_0 = -2
  t_f = 10
  N = 8
  call eulers_method(t_0, y_0, t_f, N, output)
  N = 16
  output = "output_16.txt"
  call eulers_method(t_0, y_0, t_f, N, output)
  N = 32
  output = "output_32.txt"
  call eulers_method(t_0, y_0, t_f, N, output)
  N = 64
  output = "output_64.txt"
  call eulers_method(t_0, y_0, t_f, N, output)
end program main  