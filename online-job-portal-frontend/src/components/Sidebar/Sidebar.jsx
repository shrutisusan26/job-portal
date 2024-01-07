import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Sidebar.css';

function Sidebar({ menuItems }) {
  const navigate = useNavigate();

  const handleLogout = () => {
    // Clear localStorage
    localStorage.clear();
    // Redirect to the login page
    navigate('/');
  };

  return (
    <div className='sidebar'>
  
      <div className='menu'>
        {menuItems.map((item, index) => (
          <Link key={index} to={item.link} className='menu-item'>
            {item.label}
          </Link>
        ))}
        <div className='menu-item' onClick={handleLogout}>
          Logout
        </div>
      </div>
    </div>
  );
}

export default Sidebar;