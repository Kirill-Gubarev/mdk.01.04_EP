#ifndef TERMINAL_H
#define TERMINAL_H

#include <stdint.h>

void init();
void setInstantInputMode(uint8_t enable);
int instantGetChar();

#endif//TERMINAL_H
