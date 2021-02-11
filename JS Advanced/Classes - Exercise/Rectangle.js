class Rectangle {
constructor(width, height, color){
    this.width = width;
    this.height = height;
    this.color = color.charAt(0).toUpperCase()+color.substring(1);
}
calcArea(){
    return this.width*this.height;
}
}