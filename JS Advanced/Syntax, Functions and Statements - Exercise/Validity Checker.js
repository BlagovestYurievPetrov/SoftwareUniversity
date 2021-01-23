function validation(x1, y1, x2, y2) {
    const dist1 = Math.sqrt(
      Math.abs(x1) * Math.abs(x1) + Math.abs(y1) * Math.abs(y1)
    );
    if (Number.isInteger(dist1)) {
      console.log(`{${x1}, ${y1}} to {0, 0} is valid`);
    } else {
      console.log(`{${x1}, ${y1}} to {0, 0} is invalid`);
    }
  
    const dist2 = Math.sqrt(
      Math.abs(x2) * Math.abs(x2) + Math.abs(y2) * Math.abs(y2)
    );
    if (Number.isInteger(dist2)) {
      console.log(`{${x2}, ${y2}} to {0, 0} is valid`);
    } else {
      console.log(`{${x2}, ${y2}} to {0, 0} is invalid`);
    }
    const dist3 = Math.sqrt(
      Math.abs(x1 - x2) * Math.abs(x1 - x2) +
        Math.abs(y1 - y2) * Math.abs(y1 - y2)
    );
    if (Number.isInteger(dist3)) {
      console.log(`{${x1}, ${y1}} to {${x2}, ${y2}} is valid`);
    } else {
      console.log(`{${x1}, ${y1}} to {${x2}, ${y2}} is invalid`);
    }
  }