	FROM
		UserInfo uinf
	INNER JOIN UserInterest uint
	INNER JOIN InterestList ilist
	WHERE
		uinf.city = :city
		AND uinf.country = :country
		AND uinf.sex = :sex
		AND uinf.age BETWEEN 18 AND 20
		AND uint.id = ilist.id
		AND ilist.interestGroup = 2
	GROUP BY
		uint.id
	ORDER BY
		COUNT(uint.id) DESC