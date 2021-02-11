class Stringer {
constructor(innerString,innerLength){
    this.innerString = innerString;
    this.innerLength = innerLength;
}

increase(length){
    this.innerLength += length;
}
decrease(length){
    if(length>this.innerLength){
        this.innerLength =0;
    } else {
        this.innerLength -= length;
    }

}
toString(){
    return this.innerString.substring(0,this.innerLength) + '...';
}
}