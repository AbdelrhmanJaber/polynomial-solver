import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedList {
    /**
     * Inserts a specified element at the specified position in the list.
     * @param index
     * @param element
     */
    public void add(int index, Object element);
    /**
     * Inserts the specified element at the end of the list.
     * @param element
     */
    public void add(Object element);
    /**
     * @param index
     * @return the element at the specified position in this list.
     */
    public Object get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index
     * @param element
     */
    public void set(int index, Object element);
    /**
     * Removes all of the elements from this list.
     */
    public void clear();
    /**
     * @return true if this list contains no elements.
     */
    public boolean isEmpty();
    /**
     * Removes the element at the specified position in this list.
     * @param index
     */
    public void remove(int index);
    /**
     * @return the number of elements in this list.
     */
    public int size();
    /**
     * @param fromIndex
     * @param toIndex
     * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
     */
    public ILinkedList sublist(int fromIndex, int toIndex);
    /**
     * @param o
     * @return true if this list contains an element with the same value as the specified element.
     */
    public boolean contains(Object o);
}

class node{
    int data;
    int power;
    node next;
}

class SingleLinkedList implements ILinkedList {
    /* Implement your linked list class here*/
    node head;

    public void add(int index, Object element) {
        if (index < 0)
            throw new NullPointerException();
        node temp = head;
        node link = new node();
        link.data = (int) element;
        link.next = null;
        if (index == 0) {
            link.next = head;
            head = link;
        } else {
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            link.next = temp.next;
            temp.next = link;
        }
    }

    public void add(Object element) {
        node link = new node();
        link.data = (int) element;
        link.next = null;
        if (head == null) {
            head = link;
        } else {
            node temp = head;
            while ((temp.next) != null) {
                temp = temp.next;
            }
            temp.next = link;
        }
    }
    public void add(int data , int power){
        node link = new node();
        link.data =  data;
        link.power = power;
        link.next = null;
        if(head == null){
            head = link;
        }
        else{
            node temp = head;
            while ((temp.next) != null){
                temp = temp.next;
            }
            temp.next = link;
        }
    }

    public Object get(int index) {
        int value;
        if (index < 0) {
            throw new NullPointerException();
        } else {
            node temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            value = temp.data;
        }
        return value;
    }


    public void set(int index, Object element) {
        if (index < 0) {
            throw new NullPointerException();
        } else {
            node temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            temp.data = (int) element;
        }
    }


    public void clear() {
        if (size() == 0) {
            return;
        }
        head = null;
    }


    public boolean isEmpty() {
        if (size() == 0)
            return true;
        else
            return false;
    }


    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new NullPointerException();
        }
        node temp = head;
        if (index == 0) {
            head = head.next;
        } else {
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }
    }

    public int size() {
        int counter = 0;
        node temp = head;
        while (temp != null) {
            counter++;
            temp = temp.next;
        }
        return counter;
    }

    public ILinkedList sublist(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > toIndex || fromIndex >= size() || toIndex < 0 || toIndex >= size())
            throw new NullPointerException();
        SingleLinkedList subList = new SingleLinkedList();
//        subList.clear();
        node temp = head;
        for (int i = 0; i < fromIndex; i++) {
            temp = temp.next;
        }
        head = temp; //to print the list from the desired position
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(temp.data);
            temp = temp.next;
        }
        temp.next = null;
        return subList;
    }

    public boolean contains(Object o) {
        node temp = head;
        int flag = 0;
        while (temp != null) {
            if (temp.data == (int) o) {
                flag = 1;
            }
            temp = temp.next;
        }
        if (flag == 1)
            return true;
        else
            return false;
    }

    public void print() {
        node temp = head;
        System.out.print("[");
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null)
                System.out.print(", ");
            temp = temp.next;
        }
        System.out.print("]");
    }
}

interface IPolynomialSolver {
    /**
     * Set polynomial terms (coefficients & exponents)
     * @param poly: name of the polynomial
     * @param terms: array of [coefficients][exponents]
     */
    void setPolynomial(char poly, int[][] terms);

    /**
     * Print the polynomial in ordered human readable representation
     * @param poly: name of the polynomial
     * @return: polynomial in the form like 27x^2+x-1
     */
    String print(char poly);

    /**
     * Clear the polynomial
     * @param poly: name of the polynomial
     */
    void clearPolynomial(char poly);

    /**
     * Evaluate the polynomial
     * @param poly: name of the polynomial
     * @param value: the polynomial constant value
     * @return the value of the polynomial
     */
    float evaluatePolynomial(char poly, float value);

    /**
     * Add two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return the result polynomial
     */
    int[][] add(char poly1, char poly2);

    /**
     * Subtract two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return the result polynomial*/
    int[][] subtract(char poly1, char poly2);

    /**
     * Multiply two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return: the result polynomial
     */
    int[][] multiply(char poly1, char poly2);
}


public class Solution implements IPolynomialSolver{

    public SingleLinkedList AList = new SingleLinkedList();
    public SingleLinkedList BList = new SingleLinkedList();
    public SingleLinkedList CList = new SingleLinkedList();
    public SingleLinkedList RList = new SingleLinkedList();

    public void setPolynomial(char poly, int[][] terms) {
        if(terms == null || terms.length == 0 ){
            throw new RuntimeException();
        }
        else {
            SingleLinkedList tempList = new SingleLinkedList();
            tempList.clear();
//            System.out.println("created templist");
//            System.out.println(tempList.size());
            for (int i = 0; i < terms.length; i++) {
                if(terms[i][1] < 0)
                    throw new RuntimeException();
                else
                    tempList.add(terms[i][0], terms[i][1]);
            }
            switch (Character.toUpperCase(poly)){
                case 'A':{
                    AList = tempList;
                    break;
                }
                case 'B':{
                    BList = tempList;
                    break;
                }
                case 'C':{
                    CList = tempList;
                    break;
                }
                default:{
                    throw new RuntimeException();
                }
            }
        }
    }

    public String print(char poly) {
        SingleLinkedList tempList = new SingleLinkedList();
        String res = "";
        switch (Character.toUpperCase(poly)){
            case 'A':{
                tempList = AList;
                break;
            }
            case 'B':{
                tempList = BList;
                break;
            }
            case 'C':{
                tempList = CList;
                break;
            }
            case 'R':{
                tempList = RList;
                break;
            }
            default:{
                throw new RuntimeException();
            }
        }
        node link = tempList.head;
        int size = tempList.size();
        for(int i= 0 ; i < size-2 ; i++){
            if(link.data != 0){
                if(link.data != 1){
                    res+= link.data+"x^"+ link.power;
                }
                else{
                    res+= "x^"+link.power;
                }
            }
            link = link.next;
            if(link.data > 0 && res!="")
                res+="+";
        }
        if(link.data != 0){
            if(link.data != 1){
                res+= link.data + "x";
            }
            else{
                res+= "x";
            }
        }
        link = link.next;
        if(link.data != 0){
            if(link.data > 0){
                res+= "+"+link.data;
            }
            else{
                res+= link.data;
            }
        }
        return res;
    }

    public void clearPolynomial(char poly) {
        switch (Character.toUpperCase(poly)){
            case'A':{
                AList.clear();
                AList.print();
                break;
            }
            case'B':{
                BList.clear();
                BList.print();
                break;
            }
            case'C':{
                CList.clear();
                CList.print();
                break;
            }
            case'R':{
                RList.clear();
                RList.print();
                break;
            }
            default:{
                throw new RuntimeException();
            }
        }
    }

    public float evaluatePolynomial(char poly, float value) {
        SingleLinkedList tempList = new SingleLinkedList();
//        tempList.clear();
        float res = 0;
        switch (Character.toUpperCase(poly)){
            case 'A':{
                tempList = AList;
                break;
            }
            case 'B':{
                tempList = BList;
                break;
            }
            case 'C':{
                tempList = CList;
                break;
            }
            case 'R':{
                tempList = RList;
            }
            default:{
                throw new RuntimeException();
            }
        }
        node link = tempList.head;
        for(int i=0 ; i< tempList.size() ; i++){
            res+= (float) (link.data * (Math.pow((double)value,(double)link.power)));
            link = link.next;
        }
        return res;
    }

    public int[][] ConvertListToArray(SingleLinkedList list){
        node temp = list.head;
        int[][]arr=new int[list.size()][2];
        for(int i=0 ; i < list.size() ; i++){
            arr[i][0] = temp.data;
            arr[i][1] = temp.power;
            temp = temp.next;
        }
        return arr;
    }
    public int[][] add(char poly1, char poly2) {
        SingleLinkedList temp1 = new SingleLinkedList();
        SingleLinkedList temp2 = new SingleLinkedList();
        SingleLinkedList temp3 = new SingleLinkedList();
        switch (Character.toUpperCase(poly1)){
            case'A':{
                temp1 = AList;
                break;
            }
            case'B':{
                temp1 = BList;
                break;
            }
            case'C':{
                temp1 = CList;
                break;
            }
            default:{
                throw new RuntimeException();
            }
        }
        switch (Character.toUpperCase(poly2)){
            case'A':{
                temp2 = AList;
                break;
            }
            case'B':{
                temp2 = BList;
                break;
            }
            case'C':{
                temp2 = CList;
                break;
            }
            default:{
                throw new RuntimeException();
            }
        }
        int size1 = temp1.size();
        int size2 = temp2.size();
        node list1 = temp1.head;
        node list2 = temp2.head;
        if(size1 > size2){
            for(int i=0 ; i< (size1 - size2) ; i++){
                temp3.add(list1.data , list1.power);
                list1 = list1.next;
            }
            for(int i=0 ; i< size2 ; i++){
                int val = list1.data + list2.data;
                temp3.add(val , list1.power);
                list1 = list1.next;
                list2 = list2.next;
            }
        }
        else if(size2 > size1){
            for(int i=0 ; i< (size2 - size1) ; i++){
                temp3.add(list2.data , list2.power);
                list2 = list2.next;
            }
            for(int i=0 ; i< size1 ; i++){
                int val = list1.data + list2.data;
                temp3.add(val , list2.power);
                list1 = list1.next;
                list2 = list2.next;
            }
        }
        else{
            for(int i=0 ; i < size1 ; i++){
                int val = list1.data + list2.data;
                temp3.add(val , list1.power);
                list1 = list1.next;
                list2 = list2.next;
            }
        }
        RList = temp3;
        return ConvertListToArray(temp3);
    }

    public int[][] subtract(char poly1, char poly2) {
        SingleLinkedList temp1 = new SingleLinkedList();
        SingleLinkedList temp2 = new SingleLinkedList();
        SingleLinkedList temp3 = new SingleLinkedList();
        switch (Character.toUpperCase(poly1)){
            case'A':{
                temp1 = AList;
                break;
            }
            case'B':{
                temp1 = BList;
                break;
            }
            case'C':{
                temp1 = CList;
                break;
            }
            default:{
                throw new RuntimeException();
            }
        }
        switch (Character.toUpperCase(poly2)){
            case'A':{
                temp2 = AList;
                break;
            }
            case'B':{
                temp2 = BList;
                break;
            }
            case'C':{
                temp2 = CList;
                break;
            }
            default:{
                throw new RuntimeException();
            }
        }
        int size1 = temp1.size();
        int size2 = temp2.size();
        node list1 = temp1.head;
        node list2 = temp2.head;
        if(size1 > size2){
            for(int i=0 ; i< (size1 - size2) ; i++){
                temp3.add(list1.data , list1.power);
                list1 = list1.next;
            }
            for(int i=0 ; i< size2 ; i++){
                int val = list1.data - list2.data;
                temp3.add(val , list1.power);
                list1 = list1.next;
                list2 = list2.next;
            }
        }
        else if(size2 > size1){
            for(int i=0 ; i< (size2 - size1) ; i++){
                temp3.add(-1*list2.data , list2.power);
                list2 = list2.next;
            }
            for(int i=0 ; i< size1 ; i++){
                int val = list1.data - list2.data;
                temp3.add(val , list2.power);
                list1 = list1.next;
                list2 = list2.next;
            }
        }
        else{
            for(int i=0 ; i < size1 ; i++){
                int val = list1.data - list2.data;
                temp3.add(val , list1.power);
                list1 = list1.next;
                list2 = list2.next;
            }
        }
        RList = temp3;
        return ConvertListToArray(temp3);
    }

    public int[][] multiply(char poly1, char poly2) {
        SingleLinkedList temp1 = new SingleLinkedList();
        SingleLinkedList temp2 = new SingleLinkedList();
        SingleLinkedList temp3 = new SingleLinkedList();
        switch (Character.toUpperCase(poly1)){
            case'A':{
                temp1 = AList;
                break;
            }
            case'B':{
                temp1 = BList;
                break;
            }
            case'C':{
                temp1 = CList;
                break;
            }
            default:{
                throw new RuntimeException();
            }
        }
        switch (Character.toUpperCase(poly2)){
            case'A':{
                temp2 = AList;
                break;
            }
            case'B':{
                temp2 = BList;
                break;
            }
            case'C':{
                temp2 = CList;
                break;
            }
            default:{
                throw new RuntimeException();
            }
        }
        int size1 = temp1.size();
        int size2 = temp2.size();
        node list1 = temp1.head;
        node list2 = temp2.head;
        for(int i=0 ; i < size1 ; i++){
            list2 = temp2.head;
            for(int j=0 ; j < size2 ; j++){
                int val = list1.data * list2.data;
                int pow = list1.power + list2.power;
                temp3.add(val , pow);
                list2 = list2.next;
            }
            list1 = list1.next;
        }
        RList = temp3;
        temp3 = MultiplyHelp(temp3);
        return ConvertListToArray(temp3);
    }

    public SingleLinkedList MultiplyHelp(SingleLinkedList List){
        node temp1 = List.head;
        node temp2;
        while ( temp1 !=null && temp1.next != null){
            temp2 = temp1;
            while (temp2.next != null){
                if(temp1.power == temp2.next.power){
                    temp1.data += temp2.next.data;
                    temp2.next = temp2.next.next; //remove visited node
                }
                else{
                    temp2 = temp2.next;
                }
            }
            temp1 = temp1.next;
        }
        return List;
    }

    public int[][] ConvertStringToArray(String s){
        s = s.replaceAll("\\[|\\]", "");
        String[] array = s.split(",");
        int[][] terms = new int[array.length][2];
        if(array.length == 0 || array[0].isEmpty()){
            throw new RuntimeException();
        }
        else {
            for (int i = 0; i < array.length; i++) {
                terms[i][0] = Integer.parseInt(array[i]);
                terms[i][1] = array.length - i - 1;
            }
        }
        return terms;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner input = new Scanner(System.in);
        Solution sol = new Solution();
        while (true){
            String function;
            try {
                function = input.nextLine();
            } catch (NoSuchElementException e) {
                break;
            }
            try {
                switch (function) {
                    case "set": {
                        char poly = input.nextLine().toUpperCase().charAt(0);
                        String sin = input.nextLine();
                        int[][]terms = sol.ConvertStringToArray(sin);
                        sol.setPolynomial(poly , terms);
                    }
                    break;
                    case"print":{
                        char poly = input.nextLine().toUpperCase().charAt(0);
                        String res = sol.print(poly);
                        System.out.println(res);
                    }
                    break;
                    case"eval":{
                        char poly = input.nextLine().toUpperCase().charAt(0);
                        float value = input.nextFloat();
                        int result = (int) sol.evaluatePolynomial(poly , value);
                        System.out.println(result);
                    }
                    break;
                    case"clear":{
                        char poly = input.nextLine().toUpperCase().charAt(0);
                        sol.clearPolynomial(poly);
                        break;
                    }
                    case"add":{
                        char poly1 = input.nextLine().toUpperCase().charAt(0);
                        char poly2 = input.nextLine().toUpperCase().charAt(0);
                        sol.add(poly1 , poly2);
                        System.out.println(sol.print('R'));
                    }
                    break;
                    case"sub":{
                        char poly1 = input.nextLine().toUpperCase().charAt(0);
                        char poly2 = input.nextLine().toUpperCase().charAt(0);
                        sol.subtract(poly1, poly2);
                        System.out.println(sol.print('R'));
                    }
                    break;
                    case"mult":{
                        char poly1 = input.nextLine().toUpperCase().charAt(0);
                        char poly2 = input.nextLine().toUpperCase().charAt(0);
                        sol.multiply(poly1 , poly2);
                        System.out.println(sol.print('R'));
                    }
                    break;
                    default:{
                        throw new RuntimeException();
                    }
                }
            }
            catch (RuntimeException e){
                System.out.print("Error");
                return;
            }
        }
    }
}