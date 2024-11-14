#ifndef TERMINAL_H
#define TERMINAL_H

#include <stdint.h>

void init();
void terminate();
void setInstantInputMode(uint8_t enable);
void setCursorPos(int x, int y);
void setCursorVisibility(uint8_t enable);
void setAltBuf(uint8_t enable);
int instantGetChar();

#endif//TERMINAL_H
