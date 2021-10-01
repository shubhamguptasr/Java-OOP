/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

/**
 *
 * @author Shubham
 */
public class Matrix {
    private int[][] matrixData;	// integer array to store integer data
    private int rowsNum;	// number of rows
    private int colsNum;	// number of columns

    public Matrix(int row, int col){ //constructor1
        rowsNum = row<=0? 3: row; 
        colsNum = col<=0? 3: col;
        /* constructs a row-by-col matrix with all elements equal to 0;
	if row<=0 then the number of rows of the matrix is set to 3;
	likewise, if col<=0 the number of columns of the matrix is set to 3. */
        
        matrixData = new int[rowsNum][colsNum]; //allocates memory for the array object
    }//end first constructor
    
    public Matrix(int[][] table){ // constructor2
	
        /* constructs a matrix out of the two dimensional array table,
        with the same number of rows, columns, and the same element in each
        position as array table. */

	rowsNum = table.length;
	colsNum = table[0].length; //length of row 0 giving us col
	matrixData = new int[rowsNum][colsNum]; // allocates memory for the 2D array
	
        //loop to fill the array with values:
	for (int i=0; i<rowsNum; i++){
            for(int j=0; j<colsNum; j++){
		matrixData[i][j] = table[i][j]; //copies array elements to matrixData
            }
        }
    }//end second constructor
        
    public int getRow(){ //get method for row dimension
        return rowsNum;
    }
        
    public int getCol(){ //get method for col dimension
        return colsNum;
    }
        
    public int getElement(int i, int j) throws IndexOutOfBoundsException {
	
        /* if i and j are valid indices of this matrix,
	then the element on row i and column j of this matrix
	is returned; otherwise it throws an exception with message "Invalid indexes".*/

	if(i>=0 && i<rowsNum && j>=0 && j<colsNum){ //checks if i & j exist in the range
            return matrixData[i][j];
	}
	else{
            throw new IndexOutOfBoundsException("Invalid indexes.");
	}	
    }//end getElement

    public boolean setElement(int  x, int i, int j){
        /* if i and j are valid indexes of this matrix, then the element on  row i and
        column j of this matrix is assigned the value  x and true is returned;
        otherwise false is returned and no change in the matrix is performed */
        
        if(i>=0 && i<rowsNum && j>=0 && j<colsNum){ //checks if i & j exist in the range
            matrixData[i][j] = x; //gets the value of x at ith and jth  location
            return true;
	}
	else{
            return false;
	}	
    }//end setElement

    public Matrix copy(){ 
        /* returns a deep copy of this Matrix */
		
        return new Matrix(matrixData); //calls constructor 2
    
    }//end copy

    public void addTo(Matrix m) throws ArithmeticException{
    /*adds Matrix m to this Matrix; it throws an exception message "Invalid operation"
    if the matrix addition is not defined*/

    if (this.getRow() == m.getRow() && this.getCol() == m.getCol()) { //checks if same dimension 
        //add matrices here 
        //this is the object accessing object.addTo
        
        for(int i=0; i<rowsNum;i++){
            for(int j=0; j<colsNum;j++){
                matrixData[i][j] = matrixData[i][j] + m.matrixData[i][j]; 
            }
        }
    }else{
	throw new ArithmeticException("Invalid operation");
	}
    }

    public Matrix subMatrix(int i, int j) throws ArithmeticException{
        /*  returns a new Matrix object, which represents a submatrix of this Matrix,
        formed out of rows 0 through i and columns 0 through j;
        the method should first check if values i and j are within the required range,
        and throw an exception if any of them is not. The exception detail message should read: "Submatrix not defined".
        Note: The new object should be constructed in such a way that changes in the new matrix do not affect
        this Matrix. */
    
        if(i>=0 && i<rowsNum && j>=0 && j<colsNum){ //checks if i & j exist in the range
            int subMat[][] = new int[++i][++j]; //ensures matrix is from 0 to i 
            
        for(int row=0;row<i;row++){
            for(int col=0;col<j;col++){
                subMat[row][col] = matrixData[row][col];
            }
        }
        return new Matrix(subMat);
    }else{
        throw new ArithmeticException("Submatrix not defined");
    }
    }
    
    public boolean isUpperTr(){
        for(int i=0;i<rowsNum;i++){
            for(int j=0;j<i;j++){ //ensures we skip to row 1
                if(matrixData[i][j] != 0){ //checks if element is non-zero
                    return false;
                }
            }
        }
        return true;
    }
    
    public static Matrix sum(Matrix[] matArray) throws ArithmeticException{
        Matrix outputMat = matArray[0].copy();
        
        for(int i=1; i<matArray.length;i++){
            outputMat.addTo(matArray[i]);
        }
        return outputMat;
    }
    public String toString(){
        /* returns a string representing the matrix,
        with each row on a line, and the elements in each row being separated by 2 blank spaces. */
    
        String output = new String(); // creates an empty string
        for(int i = 0; i < rowsNum; i++){
            for(int j = 0; j < colsNum; j++){
                output += matrixData[i][j] + "  ";
            }
        output += "\n";
        }
        return output;
    }//end toString
}

    

