
FC = gfortran
EXE = main
FFLAGS = -fdefault-real-8 -fdefault-double-8 -Wall -Wextra -Wconversion
OBJECTS = main.o euler.o

.PHONY: clean

$(EXE): $(OBJECTS)
	$(FC) $(FFLAGS) $(OBJECTS) -o $(EXE)

%.o : %.F90
	$(FC) $(FFLAGS) -c $<

main.o : euler.o

clean:
	rm -f $(OBJECTS) $(EXE) *.mod
