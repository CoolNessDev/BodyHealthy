import { Exercise } from '../models/exercise';

const getUrl = (img: string): string => {
  if (img.includes(':-:')) {
    let len = img.length;
    let substr = img.substring(img.indexOf(':-:'), len);
    return img.replace(substr, '');
  } else {
    return img;
  }
};
const getImageId = (img: string): string => {
  if (img.includes(':-:')) {
    let substr = img.substring(0, img.indexOf(':-:'));
    return img.replace(substr + ':-:', '');
  } else {
    return null;
  }
};
const removeExercise = (arr: Exercise[], item: Exercise) => {
  return arr.filter((i) => i.idEjercicio != item.idEjercicio);
};
const removeDropElement = (arr: any[], item: any) => {
  return arr.filter((i) => i.id != item.id);
};
// concat to arr1 if not include exercise.id
const concatUniqueExercise = (
  arr1: Exercise[],
  arr2: Exercise[]
): Exercise[] => {
  arr2.map((i) => {
    let t = true;
    arr1.map((j) => {
      if (i.idEjercicio === j.idEjercicio) {
        t = false;
      }
    });
    if (t) {
      arr1.push(i);
    }
  });
  return arr1;
};
export {
  getUrl,
  getImageId,
  removeExercise,
  removeDropElement,
  concatUniqueExercise,
};
