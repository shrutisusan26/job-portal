import React, { createContext, useState } from 'react';

export const UserContext = createContext({
  userId: null,
  setUserId: (userId) => {},
});

export const UserProvider = ({ children }) => {
  const [userId, setUserId] = useState(null);

  return (
    <UserContext.Provider value={{userId, setUserId }}>
      {children}
    </UserContext.Provider>
  );
};
