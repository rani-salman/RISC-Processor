# CSC320-Implementation-MicroProcessor

Mohamad Khalifeh
Yara Harb
Rani Salman
Ali Youssef Solh

Lebanese American University 
School of Arts and Sciences
Department of Computer Science and Mathematics

CSC 320 – Computer Organization
A Simple RISC Processor

Design Phase I: Due March 23
Design Phase II: Due April 8
Implementation Phase: Due May 4

Guidelines
- Form groups of four.
- Work on your processor design that conforms to the given system characteristics. 
Answering questions in the Design part guides you through the design details. 
- Use JAVA programming language to simulate your processor. 

Phase I Deliverables
1. Submit a report answering all questions of Design Phase I before the respective due 
date. The report shall explain and justify all design choices. 

Phase II Deliverables
1. Prepare few slides as follows: one slide to summarize the updated instruction 
structures based on feedback, one slide that presents the full datapath, and additional 
slides as needed to present the datapaths of the different types of supported 
instructions. 
2. Present and defend your datapath design.
Implementation Phase Deliverables
1. Prepare a well-documented source code of the processor.
2. Zip all files of the source code and upload to BB Learn by the due date.
3. Prepare an example program that includes all instructions supported by the processor. 
This example is to be used to demonstrate your final processor. 
4. Schedule an appointment with the course instructor and demonstrate your final 
processor. All group members should be present and prepared to answer all questions 
related to the design and implementation details.


Project Description
In this project, you are expected to design and simulate a simple RISC processor that supports 
the following 7 instructions:
- Addition/Subtraction: involves three registers, adds/subtracts two and result saved in 
third
- Immediate Addition/Subtraction: involves two registers, adds/subtracts a given constant 
to one register and result saved in second
- Load: involves two registers, loads a word from memory at an address given by one 
register and saves it to another register
- Load immediate: involves one register, loads a constant to the given register. 
- Store: involves two registers, stores the content of one register to memory at an address 
given by the second register
Assume the processor has 8 general purpose 16-bit registers: R0, R1... R7, in addition to the 
program counter (PC) register. One memory is used for instructions and data and it is limited 
to 64KB.

The control flow of the processor works in four stages:
1- Fetch: at this stage, instructions are read from the instruction memory according to 
the value of the PC register. PC is then incremented.
2- Decode: At this stage, the instruction’s opcode is decoded. The processor will know 
what type of instruction will be executed, and the inputs of the ALU are determined if 
needed. 
3- Execute: The instruction decoded in the previous stage is executed. 
4- Write back: This is the last stage where data is written to the instruction’s destination. 
For the arithmetic operations and the load operations, the destination must always be 
a register. The destination of the store operation is memory.Design - I
to guide you through your design, answer the following questions and justify your answer
1. Design the instruction structure by specifying the instruction fields and their sizes (i.e. 
number of bits required for each field) for each type of instructions. In your design, 
make sure to justify the used number of bits for each field 
2. For each given instruction, define its syntax and corresponding opcode
3. Specify unique identifiers for each of the registers
4. Specify the increment performed to the program counter 
5. Give one example instruction in assembly language of each type and convert to its 
corresponding machine language as per your design
6. Give an example C code and convert to assembly language while ensuring to use all 
supported instructions
Design – II
7. Sketch the full datapath of your processor that supports all given instructions then 
present the datapath followed for each instruction. 


Implementation

In this phase, you need to simulate the designed processor. You are required to implement 
the given instructions only. 
To implement the desired processor, you must first implement its components depending on 
their behavior:
1- Register: Saves a binary value presented at its input when its write signal is activated. 
2- Memory: Can be implemented as an array of Bytes. You do not need to create a 64KB 
memory, you can limit it to 256 Bytes for simplicity. 
3- Program Counter PC: the program counter is a special purpose register with an 
internal increment unit. 
4- ALU: takes two operands (values of the source registers) and performs the arithmetic 
operations depending on the instruction opcode. 
5- A Control Unit: It is responsible for the control flow of the program, by guiding the 
data-path’s control signals throughout the fetch, decode, execute, and write back 
stages. The output signals of the control unit are based on the instruction opcode. 
6- Microprocessor: It is the main component that connects all the above components 
according to the designed datapath. Initialization
Before running your simulated processor, you need to load the program to memory and 
initialize the PC to the address of the first instruction to be executed. The program is a 
sequence of machine language instructions based on the designed assembly language. To 
obtain the machine language, write an assembler that takes the program of interest in 
assembly language as an input and generates the corresponding machine language. 
Output
After each instruction, your program should display the contents of all the registers and the 
affected memory slots, if any. 
