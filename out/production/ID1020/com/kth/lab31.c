#include <stdio.h>
#include <ctype.h>

/**
 * ID1020
 * William Axbrink 2020-09-21
 * Lösning för labb 3 - Problem 1
 * Öppnar textfilen book.txt och itererar igenom boken och byter ut allting
 * som inte är en bokstav till mellanslag/blank space. För ökad läslighet så
 * sätts \n till \n istället för att också bli blank space.
 *
 * Filen skrivs ej över utan förblir oförändrad och istället skrivs texten ut
 * i terminalen.
 *
 *
 */
int main(int argc, char const *argv[]) {
  FILE *book;
  book = fopen("book.txt","r");
  char c;
  while((c = fgetc(book))!= EOF){
    if(isalpha(c)){
      putchar(c);
    }else if(c == '\n'){
      putchar('\n');
    }
    else{
      putchar(' ');
    }
  }
  fclose(book);
  return 0;
}
