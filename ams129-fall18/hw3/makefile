basel.exe: main.o param.o calc_basel.o
		gfortran -Wall -Wextra main.o calc_basel.o param.o -o basel.exe


param.o:		param.F90
				gfortran -c -Wall -Wextra param.F90		
main.o: 		main.F90
				gfortran -c -Wall -Wextra main.F90
calc_basel.o:	calc_basel.F90
				gfortran -c -Wall -Wextra calc_basel.F90
				
clean:
				rm calc_basel.o main.o param.o param.mod basel.exe