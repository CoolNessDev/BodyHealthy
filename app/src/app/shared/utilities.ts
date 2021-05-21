const getUrl=(img: string): string=> {
  if (img.includes(':-:')) {
    let len = img.length;
    let substr = img.substring(img.indexOf(':-:'), len);
    return img.replace(substr, '');
  } else {
    return img;
  }
}
const getImageId=(img: string): string=> {
  if (img.includes(':-:')) {
    let substr = img.substring(0, img.indexOf(':-:'));
    return img.replace(substr + ':-:', '');
  } else {
    return null;
  }
}
export {getUrl,getImageId}
