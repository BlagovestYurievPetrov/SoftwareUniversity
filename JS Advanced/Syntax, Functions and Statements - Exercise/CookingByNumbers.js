function cook(inita,cmd1,cmd2,cmd3,cmd4,cmd5) {
    const cmds = [cmd1,cmd2,cmd3,cmd4,cmd5]
    let num = Number(inita);
  for (let i = 0; i < 5; i++) {
    switch (cmds[i]){
      case 'chop':
        num/=2;
      break;
      case 'dice':
        num = Math.sqrt(num);
      break;
      case 'spice':
        num++;
      break;
      case 'bake':
        num*=3;
      break;
      case 'fillet':
        num*=0.8;
      break;
    }
    console.log(num);
  }
  }