function Errors({ errors = [] }) {
  if (errors.length === 0) {
    return null;
  }

  return (
    <div className="alert alert-danger">
      {errors.map((error) => (
        <li key={error}>{error}</li>
      ))}
    </div>
  );
}

export default Errors;
