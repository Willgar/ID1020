/**
  * ID1020
  * Första lösningen för labb 1 - Problem 1.
  * Använder en rekursiv och iterativ metod för att läsa in text från terminalen och sedan printa ut det i bakvänd ordning.
  */
#include <stdio.h>
void recursiveC();
void iterativeC(char* sentence);

int main(){
  char sentence[16];
  recursiveC();
  printf("\n");
  iterativeC(sentence);
}
/**
  * Rekursivt hämtar samtliga characters från terminalen tills den når EOF/(\n)
  * sedan går den baklänges tillbaka och printar ut alla characters
  */
void recursiveC(){
  char c = getchar();

  if(c!='\n'){
    recursiveC();
    putchar(c);
  } else return;
}
/**
  * Iterativt hämtar samtliga characters från texten och lagrar dem i en char array
  * via en pekare. När den når EOF/(\n) printar den ut samtliga characters från
  * arrayen startandes bakifrån.
  */
void iterativeC(char* sentence){
  int n = 0;
  char c = getchar();
  while(c != '\n'){
    sentence[n++] = c;
    c = getchar();
  }
  for(int i = n; i>=0; i--){
    putchar(sentence[i]);
  }
}
