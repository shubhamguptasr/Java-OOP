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
public class UpperTriangularMatrix {
    private int size;
    private int matrixData[];
    
    public UpperTriangularMatrix(int n){ 
        size = n;
        
        if(size<=0){  //checks if number of row is negative or zero
            size = 1; //and sets it to 1
        }
        matrixData = new int[size*(size+1)/2]; //creates a main diagonal matrix
    }

    public UpperTriangularMatrix(Matrix upTriM) throws IllegalArgumentException {
        if(upTriM.isUpperTr() && upTriM.getRow() == upTriM.getCol()){ //checks if matrix is upper triangle and rows == col
            size = upTriM.getRow();
       
        int i,j;
        int count=0;
        matrixData = new int[size*(size+1)/2];
    
        for(i=0;i<size;i++){
            for(j=0;j<i+1;j++){
                matrixData[count]= upTriM.getElement(i, j);
            }
            count++;
        }
        }else{
            throw new IllegalArgumentException("Parameter passed is not upper triangular");
        }       
    }

    public int getDim(){
        return size;
    }
    
    public int getElement(int i, int j) throws  IndexOutOfBoundsException {
        int tempMat[][]=new int[size][size];
        
        int count=0;
        int row,col;
        
        for(row=0;row<size;row++){
            for(col=0;j<row+1;col++){
                tempMat[row][col]= matrixData[count];
                count++;
            }
        }
        if(i>=0 && i<size && j>=0 && j<size){
            return tempMat[i][j];
        }else{
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }
    
    public void setElement(int x, int i, int j) throws IndexOutOfBoundsException, IllegalArgumentException {
        
        if(!(i<j) && x !=0){ //if i j position in lower part 
            throw new IllegalArgumentException("Incorrect Arguement");
        }
        else if(i<0 || !(i<size) || j<0 || !(j<size)){ //if i and j not valid positions
            throw new IndexOutOfBoundsException("Invalid Indexes");
        }else{
            int [][] store = new int[size][size];
            int count =0;
            int row,col;
            
            for(row=0;row<size;row++){
                for(col=0;col<row+1;col++){
                    store[row][col] = matrixData[count];
                    count =count + 1;
                }
            }
            store[i][j]=x;
            count = 0;
            matrixData = new int[size*(size+1)/2];
         
            for(row=0;row<size;row++){
                for(col=0;col<row+1;col++){
                    matrixData[count]= store[j][col];
                    count+=1;
                }
            }
        }
    }
    
    public Matrix fullMatrix(){ //returns the entire matrx
        
        int tempMat[][]=new int[size][size];
        
        int count=0;
        int row,col; 
        
        for(row=0;row<size;row++){
            for(col=0;col<row+1;col++){
                tempMat[row][col]= matrixData[count];
                count++;
            }
        }
        Matrix fullmatA= new Matrix(tempMat);
        return fullmatA;
    }
    
    public void print1DArray(){
        
        String out = new String();
        
        int i;
        for(i=0;i<matrixData.length;i++){
            out= out + matrixData[i]+"";
        }
        out = out + "\n";
        System.out.println(out);
    }
    
    public String toString(){
        
        int tempMat[][]=new int[size][size];
        int count =0;
        int row,col;
        
        for(row=0;row<size;row++){
            for(col=0;col<row+1;col++){
                tempMat[row][col]=matrixData[count];
                count++;
            }
        }
         Matrix matA= new Matrix(tempMat);
         
         String output = new String();
         output = matA.toString();
         return output;    
    }
    
    public int getDet(){ //uppertriangular matrix det = multiplicatio of diagonals!
        int det = 1;
        int count =0;
        int row,col;
        
        for(row=0;row<size;row++){
            for(col=0;col<size;col++){
                if(row==col){
                det= det* getElement(row, col);
                        //matrixData[count];
                //count++;
            }
            }
        }
        return det;
    }
    
    public double [] effSolve(double [] b) throws IllegalArgumentException {
        
        if(this.getDet() == 0){
            throw new IllegalArgumentException("Passed parameters not correct");
        }
        else{
            double[] x = new double[size];
            
            for(int row=0; row<size; row++){
                for(int col=0; col <size; col++){
                    x[row] = x[row] + b[row] * (double)matrixData[row + col*size]/this.getDet();
                }
            }
        return x;
        }
    }
}
