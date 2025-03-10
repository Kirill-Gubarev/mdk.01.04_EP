#ifndef TERMINAL_H
#define TERMINAL_H

#include <stdint.h>

typedef struct{
	int x;
	int y;
} Point;

void init();
void terminate();
void setInstantInputMode(uint8_t enable);
void setCursorPos(Point p);
Point getCursorPos();
void setCursorVisibility(uint8_t enable);
void setAltBuf(uint8_t enable);
int instantGetChar();

#endif//TERMINAL_H
