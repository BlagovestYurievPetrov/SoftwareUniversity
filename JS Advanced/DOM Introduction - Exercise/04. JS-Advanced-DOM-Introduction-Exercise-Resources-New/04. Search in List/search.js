function search() {
   let listItems = document.querySelectorAll('#towns>li');
   let input = document.querySelector('input').value;
   let result = document.getElementById('result');
   let counter = 0;
   for (const li of listItems) {
      if (li.textContent.toLowerCase().includes(input.toLowerCase())) {
         li.style.fontWeight = 'bold';
         li.style.textDecoration = 'underline';
         counter++;
      } else {
         li.style.fontWeight = '';
         li.style.textDecoration = '';
      }
   }
   if (counter === 1){
      result.textContent = counter + ' match found';
   } else {
      result.textContent = counter + ' matches found';
   }
}
