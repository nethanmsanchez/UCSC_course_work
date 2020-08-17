! /hw3/calc_basel.F90

subroutine calc_basel(threshold)
    use param
    implicit none
    real (kind=8), intent(in) :: threshold
    real (kind=8) :: x, y, n, error
    x = (pi*pi)/6
    n = 0
    error = x - y
    open(unit=23, file='results.txt')
    do
        n = n + 1
        if (error<threshold) exit
        y = y + 1/(n ** 2)
        error = x - y
        if(MOD(n, 1000.0) == 0.0) then
            write(23,*) "n =  ", n, "    error = ", error
        end if
    end do
    write(23,*) ""
    write(23,*) "total number of cycles = ", n, "numerical solution = ", y, "error = ", error
    close(23)
end subroutine calc_basel
