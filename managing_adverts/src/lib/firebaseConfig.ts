import { initializeApp } from 'firebase/app';
import { getFirestore } from 'firebase/firestore';
import { getAuth } from 'firebase/auth';

const firebaseConfig = {
    apiKey: "AIzaSyBgtakbHVy1Leube2cOK2DqT-n8N7qXUYw",
    authDomain: "advert-43252.firebaseapp.com",
    projectId: "advert-43252",
    storageBucket: "advert-43252.firebasestorage.app",
    messagingSenderId: "236536300034",
    appId: "1:236536300034:web:11c6c54f75bd509b8b5d1b",
    measurementId: "G-DJ2DW0PP2V"
};

const app = initializeApp(firebaseConfig);
const db = getFirestore(app);
const auth = getAuth(app);

export { db, auth };
