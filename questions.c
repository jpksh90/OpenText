#include <stdio.h>
#include <stdlib.h>
#include <wchar.h>
#define PASSWORD "ABCD1234!"
/*You need not worry about other include statements if at all any are missing */

void func1()
{
    char *data;
    char *dataBuffer = (char *)ALLOCA(100 * sizeof(char)); // ALLOCA may lead to an undefined behaviour.
    memset(dataBuffer, 'A', 100 - 1);
    dataBuffer[100 - 1] = '\0';
    data = dataBuffer - 8;
    {
        char source[100];
        memset(source, 'C', 100 - 1);
        source[100 - 1] = '\0';
        // Data points to an address 8 bytes before the base address of data buffer. It can lead to a buffer overrun
        // where it starts writing from dataBuffer-8.
        strcpy(data, source); 
        if (data != NULL)
        {
            printf("%s\n", data);
        }
    }
}

void func2()
{
    char *data;
    data = NULL;
    data = (char *)calloc(100, sizeof(char));
    // strcpy is unsafe function as it does not perform bounds check. However, in this case, the data is less
    // than size of the buffer, so the call to strcpy is safe. Having said that, it can be replaced with
    // strncpy for a safer alternative.
    strcpy(data, "A String"); 
    if (data != NULL) 
    {
        printf("%s\n", data);
    }
}

void func3()
{
    char *password;
    char passwordBuffer[100] = "";
    password = passwordBuffer;
    strcpy(password, PASSWORD);
    {
        HANDLE pHandle;
        char *username = "User";
        char *domain = "Domain";
        /* Let's say LogonUserA is a custom authentication function*/
        if (LogonUserA(
                username,
                domain,
                password,
                &pHandle) != 0)
        {
            printf("User logged in successfully.\n");
            CloseHandle(pHandle);
        }
        else
        {
            // The handle is not closed on the login failure and is kept open. 
            printf("Unable to login.\n"); 
        }
    }
}

static void func4()
{
    char *data;
    data = NULL;
    data = (char *)calloc(20, sizeof(char));
    if (data != NULL)
    {
        // strcpy is unsafe function as it does not perform bounds check. However, in this case, the data is less
        // than size of the buffer, so the call to strcpy is safe. Having said that, it can be replaced with
        // strncpy for a safer alternative.
        strcpy(data, "Initialize"); 
        if(data != NULL) 
        {
            printf("%s\n", data);
        }
        free(data);
    }
}

void func5()
{
    int i = 0;
    do
    {
        printf("%d\n", i);
        i = (i + 1) % 256; 
        // could run into an infinite loop. This program could starve other program from accessing system resources leading to a denial-of-service to other processes
    } while (i >= 0);
}

void func6()
{
    char dataBuffer[100] = "";
    char *data = dataBuffer;
    printf("Please enter a string: "); 
    // If the user inputs more than 100 characters, the fgets will not flush the buffer and will keep all the 
    // unread characters in the buffer. This buffer may be read by subsequent processes or operations leading to the leak
    // of (sensitive) information.
    if (fgets(data, 100, stdin) < 0)
    {
        printf("fgets failed!\n");
        exit(1);
    }
    if(data != NULL) 
    {
        printf("%s\n", data);
    }
}

void func7()
{
    char *data;
    data = "Fortify";
    data = NULL;
    // This is the case of null pointer dereference where the program is most likely to crash while dereferencing the data variable.
    printf("%s\n", data); 
}

int main(int argc, char *argv[])
{
    printf("Calling func1\n");
    func1();

    printf("Calling func2\n");
    func2();

    printf("Calling func3\n");
    func3();

    printf("Calling func4\n");
    func4();

    printf("Calling func5\n");
    func5();

    printf("Calling func6\n");
    func6();

    printf("Calling func7\n");
    func7();

    return 0;
}
