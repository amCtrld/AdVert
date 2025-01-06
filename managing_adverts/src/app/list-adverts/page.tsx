'use client';

import { useEffect, useState } from 'react';
import { db } from '../../lib/firebaseConfig';
import { collection, getDocs } from 'firebase/firestore';

const ListAdverts = () => {
  const [adverts, setAdverts] = useState([]);

  useEffect(() => {
    const fetchAdverts = async () => {
      try {
        const querySnapshot = await getDocs(collection(db, 'adverts'));
        const advertsData = querySnapshot.docs.map((doc) => ({
          id: doc.id,
          ...doc.data(),
        }));
        setAdverts(advertsData);
      } catch (error) {
        console.error('Error fetching adverts:', error);
      }
    };

    fetchAdverts();
  }, []);

  const getStatusColor = (status) => {
    switch (status.toLowerCase()) {
      case 'pending':
        return 'bg-yellow-100 text-yellow-800';
      case 'approved':
        return 'bg-green-100 text-green-800';
      case 'rejected':
        return 'bg-red-100 text-red-800';
      default:
        return 'bg-gray-100 text-gray-800';
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 py-8 px-4 sm:px-6 lg:px-8">
      <div className="max-w-4xl mx-auto">
        <h1 className="text-3xl font-bold text-gray-900 mb-8 text-center">
          Adverts
        </h1>
        
        {adverts.length === 0 ? (
          <div className="text-center py-12 bg-white rounded-lg shadow">
            <p className="text-gray-500">No adverts found</p>
          </div>
        ) : (
          <ul className="space-y-4">
            {adverts.map((advert) => (
              <li 
                key={advert.id} 
                className="bg-white rounded-lg shadow-sm p-6 transition-transform hover:scale-[1.02] duration-200"
              >
                <div className="flex justify-between items-start mb-4">
                  <h2 className="text-xl font-semibold text-gray-900">
                    {advert.title}
                  </h2>
                  <div className="flex gap-2">
                    <span className={`inline-flex items-center px-2.5 py-0.5 rounded-full text-sm font-medium ${getStatusColor(advert.status)}`}>
                      {advert.status}
                    </span>
                    <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-sm font-medium bg-blue-100 text-blue-800">
                      {advert.size}
                    </span>
                  </div>
                </div>
                
                <div className="prose prose-sm max-w-none text-gray-600">
                  <p className="whitespace-pre-wrap">{advert.content}</p>
                </div>
                
                {advert.createdAt && (
                  <div className="mt-4 text-sm text-gray-500">
                    Created: {advert.createdAt.toDate().toLocaleDateString()}
                  </div>
                )}
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
};

export default ListAdverts;