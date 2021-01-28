function solve() {
  let text = document.getElementById('text').value;
  let format = document.getElementById('naming-convention').value;
  text = text.toLowerCase();
  text = text.split(' ');
  let output = '';
  if (format == "Camel Case") {
    output = text[0];
  } else if (format == "Pascal Case") {
    output = text[0].charAt(0).toUpperCase() + text[0].slice(1);
  } else {
    output = 'Error!';
  }
  if (output !== 'Error!')  {
  for (let i = 1; i< text.length; i++) {
    let word = text[i];
    word = word.charAt(0).toUpperCase() + word.slice(1);
    output += word;      
  }
}
    document.getElementById('result').textContent = output;
}