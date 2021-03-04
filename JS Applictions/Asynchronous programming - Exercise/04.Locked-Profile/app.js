async function lockedProfile() {
    let response = await fetch(`http://localhost:3030/jsonstore/advanced/profiles`);
    let data = await response.json();
    let info = Object.values(data);
    console.log(Object.values(data));
    let main = document.getElementById('main');
    main.innerHTML = '';

    info.map((x)=>{
      let profile = document.createElement('div');
      profile.className = 'profile';
      let img = document.createElement('img');
      img.src = `./iconProfile2.png`;
      img.className = 'userIcon';
      let lockLabel = document.createElement('label');
      lockLabel.textContent = 'Lock';
      let lock = document.createElement('input');
      lock.type = 'radio';
      lock.name = 'userLocked';
      lock.value = 'lock';
      lock.checked = true;
      let unlockLabel = document.createElement('label');
      unlockLabel.textContent = 'Unlock';

      let unlock = document.createElement('input');
      unlock.type = 'radio';
      unlock.name = 'userLocked';
      unlock.value = 'unlock';
      let br = document.createElement('br');
      let hr = document.createElement('hr');
      let nameLabel = document.createElement('label');
      nameLabel.textContent = 'Username';
      let userInput = document.createElement('input');
      userInput.type = 'text';
      userInput.name = 'user1Username';
      userInput.value = x.username;
      userInput.readOnly = true;
        //hidden info after show more event

      let hiddenDiv = document.createElement('div');
      let hr2 = document.createElement('hr');
      hiddenDiv.appendChild(hr2);
      let emailLabel = document.createElement('label');
      emailLabel.textContent = 'Email:';
      let emailInput = document.createElement('input');
      emailInput.type = 'email';
      emailInput.name = 'user1Email';
      emailInput.value = x.email;
      emailInput.readOnly = true;
    hiddenDiv.appendChild(emailLabel);
    hiddenDiv.appendChild(emailInput);

    let ageLabel = document.createElement('label');
    ageLabel.textContent = 'Age:';
      let ageInput = document.createElement('input');
      ageInput.type = 'age';
      ageInput.name = 'user1age';
      ageInput.value = x.age;
      ageInput.readOnly = true;
      hiddenDiv.appendChild(ageLabel);
      hiddenDiv.appendChild(ageInput);
    
      hiddenDiv.style.display = 'none';
      let button = document.createElement('button');
      button.textContent = 'Show more';

      profile.appendChild(img);
      profile.appendChild(lockLabel);
      profile.appendChild(lock);
      profile.appendChild(unlockLabel);
      profile.appendChild(unlock);
      profile.appendChild(br);
      profile.appendChild(hr);
      profile.appendChild(nameLabel);
      profile.appendChild(userInput);
      profile.appendChild(hiddenDiv);
      profile.appendChild(button);
      main.appendChild(profile);


      button.addEventListener('click', e=>{
          if (!lock.checked&&unlock.checked){
              hiddenDiv.style.display === 'none'?hiddenDiv.style.display='block':hiddenDiv.style.display='none';
              button.textContent === 'Show more'?button.textContent='Show less':button.textContent='Show more';
          }

    })


    })

   
}