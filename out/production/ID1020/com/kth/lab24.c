#include <stdio.h>
void moveNegative(int array[], int size);
void printArray(int array[], int size);
int main(){
  int array[] = {-1,4,3,-8,5,6,-10,-2,-3,-4};
  printArray(array, 10);
  moveNegative(array,10);
}

void moveNegative(int array[], int size){
  int j = 0;
  int temp = 0;
    for(int i = 0; i<size; i++){
      if(array[i]<0){
        temp = array[j];
        array[j++] = array[i];
        array[i] = temp;
      }
    }
    printArray(array,10);
}
void printArray(int array[], int size){
  for(int i = 0; i<size; i++){
    printf("%d, ", array[i]);
  }
  printf("\n");
}
