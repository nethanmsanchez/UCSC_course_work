#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <errno.h>
#include <string.h>
#include <stdlib.h>
#include <sys/wait.h>
#include<fcntl.h> 

extern char ** get_args();

int
main()
{
    int         i;
    char **     args;
    pid_t		pro; // might have to fix later
    int			haslessthan = -1;//     <
    int			hasgreaterthan = -1; // >
    int			hasdouble = -1;//       >>
    int			haspipe = -1;//         |
    int 		hasgreateramp = -1;//    >&
    int			hasdubgreateramp = -1;// >>&
    int			haspipeamp = -1;//       |&
    char *		test;
    int			fd;
    int			fd3[2];
    int			hassemi = -1;
    int *		num;
    int			counter = 0;

    //printf("Nethan's Shell $XXX: ");
    while (1) {
    	haslessthan = -1;
    	hasgreaterthan = -1;
    	hasdouble = -1;
    	haspipe = -1;
    	hasgreateramp = -1;
    	hasdubgreateramp = -1;
    	haspipeamp = -1;
    	counter = 0;
    	printf("\nNethan's Shell $: ");
    	args = get_args();
    	for (i = 0; args[i] != NULL; i++) {
    		//counter++;
    	}
    	num = malloc(sizeof(int)*i);
    	for (i = 0; args[i] != NULL; i++) {
    		if( !strcmp(args[i], "<")) {
    			haslessthan = i;
    		}
    		if( !strcmp(args[i], ">")){
    			hasgreaterthan = i;
    		}
    		if( !strcmp(args[i], ">>")){
    			hasgreaterthan = i;
    			hasdouble = 1;
    		}
    		if( !strcmp(args[i], "|")){
    			haspipe = i;
    		}
    		if( !strcmp(args[i], ">&")){
    			hasgreateramp = i;
    		}
    		if( !strcmp(args[i], ">>&")){
    			hasdubgreateramp = i;
    		}
    		if( !strcmp(args[i], "|&")){
    			haspipeamp = i;
    		}
    		if( ! strcmp(args[i], ";")){
    			num[counter] = i;
    			counter++;
    			hassemi = i;
    		}
    	}
    	if( !strcmp (args[0], "exit")){
    		if(i == 2){
    			if( !strcmp (args[1], "()")){
    				//printf("found exit\n");
    				break;
    			}
    		}
    		//break; // gets here if command is exit w/out ()
    	}
    	
    	// *************  command with no arguments ****************
    	if(i == 1){
    		if( !strcmp(args[0], "cd")){
    			chdir(getenv("HOME"));
    			//printf("homedir= %s", getenv("HOME"));
    		}
    		if(fork() == 0){
    			//printf("child process running");
    			execvp(args[0], args);
    			printf("execvp returned, invalid command with no arguments");
    			exit(0);
    		} else {
    			wait(&pro);
    			//printf("\nNethan's Shell $XXX: ");
    		}
    	}
    	// *********************************************************
    	// ******* command w/ one or more args *********************
    	if(i >= 2){
    		//if( !strcmp(args[0], "cd")){
    		//	if( args[1] != NULL){
    		//		chdir(args[1]);
    		//	}
    		//}
    		if( haslessthan >= 0){
    			//haslessthan = -1;
    			fd = open(args[haslessthan+1], O_RDONLY | O_CREAT);
    			//printf("\nargs[haslessthan+1]= %s", args[haslessthan+1]);
    			if(fork() == 0){
    				if(dup2(fd,0) < 0){
    					close(fd);
    					printf("\ndup2 failed");
    					exit(0);
    				}
    				//printf("\ngot here");
    				//scanf("%s", test);
    				args[haslessthan] = NULL;
    				//printf("\ntest= %s", args[0]);
    				execvp(args[0], args);
    				//close(fd);
    				exit(0);
    			} else {
    				wait(&pro);
    				close(fd);
    	   			//printf("\nNethan's Shell $XXX: ");
    			}
    			
    		}else if( hasgreaterthan >= 0) {
    			if(hasdouble == 1){
    				fd = open(args[hasgreaterthan+1], O_WRONLY | O_APPEND | O_CREAT, 0666);
    			} else {
    				fd = open(args[hasgreaterthan+1], O_WRONLY | O_TRUNC | O_CREAT, 0666);
    			}
    			if(fork() == 0){
    				if(dup2(fd,1) < 0){
    					close(fd);
    					printf("\ndup2.2 failed");
    					exit(0);
    				}
    				args[hasgreaterthan] = NULL;
    				execvp(args[0], args);
    				exit(0);
    			} else {
    				wait(&pro);
    				close(fd);
    			}
    		}else if( haspipe >= 0 ){
    			args[haspipe] = NULL;
    			pipe(fd3);
    			//fflush(stdout);
    			if(fork() == 0){
    				//perror("in child 1");
    				if(dup2(fd3[1], 1) < 0){
    				    close(fd3[1]);
    				    printf("dup2.4 failed");
    				    exit(0);
    				}
    				close(fd3[0]);
    				close(fd3[1]);
    				execvp(args[0], args);
    				exit(0);
    			}
    			//fflush(stdout);
    			if(fork() == 0){
    				//perror("in child 2");
    				if(dup2(fd3[0], 0) < 0){
    				    close(fd3[0]);
    				    printf("dup2.4 failed");
    				    exit(0);
    				}
    				close(fd3[1]);
    				close(fd3[0]);
    				execvp(args[haspipe+1], &args[haspipe+1]);
    				exit(0);
    			}else{
    				//fflush(stdout);
    				close(fd3[0]);
    				close(fd3[1]);
    				//perror("in parent");
    				wait(0);
    				wait(0);
    			}
    		}else if( hasgreateramp >= 0){
    			fd = open(args[hasgreateramp+1], O_WRONLY | O_TRUNC | O_CREAT, 0666);
      			if(fork() == 0){
        			if(dup2(fd,1) < 0){
        				close(fd);
        				printf("\ndup2.2 failed");
        				exit(0);
        			}
        			if(dup2(fd,2) < 0){
        				close(fd);
        				printf("\ndup2.2 failed");
        				exit(0);
        			}
        			close(fd);
        			args[hasgreateramp] = NULL;
        			execvp(args[0], args);
        			exit(0);
        		} else {
        			wait(&pro);
        			close(fd);
        		}
    		}else if( hasdubgreateramp >= 0){
    			fd = open(args[hasdubgreateramp+1], O_WRONLY | O_APPEND | O_CREAT, 0666);
      			if(fork() == 0){
        			if(dup2(fd,1) < 0){
        				close(fd);
        				printf("\ndup2.2 failed");
        				exit(0);
        			}
        			if(dup2(fd,2) < 0){
        				close(fd);
        				printf("\ndup2.2 failed");
        				exit(0);
        			}
        			close(fd);
        			args[hasdubgreateramp] = NULL;
        			execvp(args[0], args);
        			exit(0);
        		} else {
        			wait(&pro);
        			close(fd);
        		}
    		} else if( haspipeamp >= 0) {
    			args[haspipeamp] = NULL;
    			pipe(fd3);
    			if(fork() == 0){
    				//perror("in child 1");
    				if(dup2(fd3[1], 1) < 0){
    				    close(fd3[1]);
    				    printf("dup2.6 failed");
    				    exit(0);
    				}
    				if(dup2(fd3[1], 2) < 0){
    				    close(fd3[1]);
    				    printf("dup2.7 failed");
    				    exit(0);
    				}
    				close(fd3[0]);
    				close(fd3[1]);
    				execvp(args[0], args);
    				exit(0);
    			}
    			//fflush(stdout);
    			if(fork() == 0){
    				//perror("in child 2");
    				if(dup2(fd3[0], 0) < 0){
    				    close(fd3[0]);
    				    printf("dup2.8 failed");
    				    exit(0);
    				}
    				close(fd3[1]);
    				close(fd3[0]);
    				execvp(args[haspipeamp+1], &args[haspipeamp+1]);
    				exit(0);
    				
    			}else{
    				//fflush(stdout);
    				close(fd3[0]);
    				close(fd3[1]);
    				//perror("in parent");
    				wait(0);
    				wait(0);
    			}
    		} else if( hassemi >= 0){
    			for(int x=0; x<counter; x++){
    				args[num[x]] = NULL;
    				if(fork() == 0){
    					execvp(args[num[x]-1], &args[num[x]-1]);
    					exit(0);
    				}else{
    					wait(0);
    				}
    			}
    			if(fork() == 0){
    				execvp(args[i-1], &args[i-1]);
    				exit(0);
    			}else{
    				wait(0);
    			}
    		}else if( !strcmp(args[0], "cd")){
    			if( args[1] != NULL){
    				chdir(args[1]);
    			}
    		}else {// has more than two
        		if(fork() == 0){
        		    //printf("child process running");
        		    execvp(args[0], args);
        		    printf("execvp returned, invalid command with no arguments");
        		    exit(0);
        		} else {
        		    wait(&pro);
        		    //printf("\nNethan's Shell $XXX: ");
        		}
        	}
    	}
    	
    	// **********************************************************
    	
    	//break;
	/*printf ("Command ('exit' to quit): ");
	args = get_args();
	for (i = 0; args[i] != NULL; i++) {
	    printf ("Argument %d: %s\n", i, args[i]);
	}
	if (args[0] == NULL) {
	    printf ("No arguments on line!\n");
	} else if ( !strcmp (args[0], "exit")) {
	    printf ("Exiting...\n");
	    break;
	}*/
    	//printf("Nethan's Shell $XXX: ");
    }// end of while(1)
    free(&num);
}// end of main()
