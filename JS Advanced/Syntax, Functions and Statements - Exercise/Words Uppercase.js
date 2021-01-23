function uppper(input) {
    console.log(
      input
        .split(/[\W]+/)
        .filter((w) => w != '')
        .map((w) => w.toUpperCase())
        .join(', ')
    );
  }
  