#include<stdio.h>
#include<unistd.h>

int main(){
    int pipe1[2], pipe2[2];
    int rmp1, rmp2;
    int pid;
    char m1[20], m2[20], recievedmsg[20];
    
    printf("Enter Message to write from parent :");
    fgets(m1, sizeof(m1), stdin);
    
    printf("Enter Message to write message from child :");
    fgets(m2, sizeof(m2), stdin);
    
    rmp1 = pipe(pipe1);
    if(rmp1==-1){
        printf("Error while creating pipe1");
    }
    
    rmp2 = pipe(pipe2);
    if(rmp2==-1){
        printf("Error while creating pipe2");
    }
    
    pid = fork();
    if(pid!=0){
        // Parent Process
        close(pipe1[0]); // close read of pipe1
        close(pipe2[1]); // close write end of pipe2
        printf("Writing msg from parent : %s\n",m1);
        write(pipe1[1],m1,sizeof(m1));
        
        read(pipe2[0], recievedmsg, sizeof(recievedmsg));
        printf("Msg recieved at parent process : %s\n", recievedmsg);
    }else{
        // Child Process
        close(pipe1[1]); // close write of pipe1
        close(pipe2[0]); // close read end of pipe2
        printf("Writing msg from child : %s\n",m2);
        write(pipe2[1],m2,sizeof(m2));
        
        read(pipe1[0], recievedmsg, sizeof(recievedmsg));
        printf("Msg recieved at child process : %s\n", recievedmsg);
    }
}