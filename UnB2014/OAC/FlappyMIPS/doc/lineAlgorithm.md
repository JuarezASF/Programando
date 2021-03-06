#Bresenham's line algorithm
Algoritmo para determinar quais pixeis devem ser pintados para traçar uma linha entre dois pontos. 
* Texto retirado da wikipedia:

>
The Bresenham line algorithm is an algorithm which determines which points in an n-dimensional raster should be plotted in order to form a close approximation to a straight line between two given points. It is commonly used to draw lines on a computer screen, as it uses only integer addition, subtraction and bit shifting, all of which are very cheap operations in standard computer architectures. It is one of the earliest algorithms developed in the field of computer graphics. A minor extension to the original algorithm also deals with drawing circles.
>
While algorithms such as Wu's algorithm are also frequently used in modern computer graphics because they can support antialiasing, the speed and simplicity of Bresenham's line algorithm means that it is still important. The algorithm is used in hardware such as plotters and in the graphics chips of modern graphics cards. It can also be found in many software graphics libraries. Because the algorithm is very simple, it is often implemented in either the firmware or the graphics hardware of modern graphics cards.


##Algoritmo
Convensão:
* the top-left is (0,0) such that pixel coordinates increase in the right and down directions (e.g. that the pixel at (7,4) is directly above the pixel at (7,5)), and
that the pixel centers have integer coordinates.

* The endpoints of the line are the pixels at (x0, y0) and (x1, y1), where the first coordinate of the pair is the column and the second is the row.


        function line(x0, x1, y0, y1)
             int deltax := x1 - x0
             int deltay := y1 - y0
             real error := 0
             real deltaerr := abs (deltay / deltax)
             int y := y0
             for x from x0 to x1
                 plot(x,y)
                 error := error + deltaerr
                 if error ≥ 0.5 then
                     y := y + 1
                     error := error - 1.0
 
notas:
* Assume deltax != 0 (line is not vertical);
* note that this division needs to be done in a way that preserves the fractional part

##Algoritmo Otimizado e Simplificado
O algoritmo acima é o que foi rapidamente implementado e funciona. O problema dele é que usa aritimética de ponto flutuante e só traçar retas crescentes. O algoritmo a seguir é mais eficiente e traçar a reta em todos os sentidos.


       function line(x0, y0, x1, y1)
           dx := abs(x1-x0)
           dy := abs(y1-y0) 
           if x0 < x1 then sx := 1 else sx := -1
           if y0 < y1 then sy := 1 else sy := -1
           err := dx-dy
 
           loop
             plot(x0,y0)
             if x0 = x1 and y0 = y1 exit loop
             e2 := 2*err
             if e2 > -dy then 
               err := err - dy
               x0 := x0 + sx
             end if
             if e2 < dx then 
               err := err + dx
               y0 := y0 + sy 
             end if
           end loop
           
Resultados:
* O algoritmo funciona bem para a maioria dos casos.
* O algoritmo falha em traçar retas com inclinação de 45º graus e de (180 + 45º); para as outras inclinações parece funcionar bem